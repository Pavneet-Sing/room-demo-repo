package com.example.pavneet_singh.room_demo_kotin_mvvm_dagger.ui

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pavneet_singh.room_demo_kotin_mvvm_dagger.R
import com.example.pavneet_singh.room_demo_kotin_mvvm_dagger.adapter.NotesAdapter
import com.example.pavneet_singh.room_demo_kotin_mvvm_dagger.notedb.NoteDataBase
import com.example.pavneet_singh.room_demo_kotin_mvvm_dagger.notedb.model.Note
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.*

/**
 * Created by Pavneet_Singh on 2020-01-25.
 */

class NoteListActivity : AppCompatActivity(), NotesAdapter.OnNoteItemClick {
    private lateinit var textViewMsg: TextView
    private lateinit var recyclerView: RecyclerView
    private lateinit var noteDatabase: NoteDataBase
    private lateinit var notes: MutableList<Note>
    private lateinit var notesAdapter: NotesAdapter
    private var pos = 0
    private lateinit var optionDialog: AlertDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initializeVies()
        displayList()
    }

    /**
     * Setup the database and live observer to show the list
     * You can use anonymous class instead of
     * [Lambda expressions or Method reference](https://developer.android.com/studio/write/java8-support#supported_features) for observer as
     * <pre>`noteDatabase
     * .getNoteDao()
     * .getNotes()
     * .observe(NoteListActivity.this, new Observer<List<Note>>() {
     *
     *
     * public void onChanged(List<Note> notes) {
     * // process notes result
     * }
     * }`</pre>
     */
    private fun displayList() {
        noteDatabase = NoteDataBase.getInstance(this@NoteListActivity)
        noteDatabase.getNoteDao().getNotes().observe(this@NoteListActivity,
            Observer<List<Note>> { noteList: List<Note> ->
                updateList(noteList)
            }
        )
    }

    private fun initializeVies() {
        val toolbar =
            findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        textViewMsg = findViewById(R.id.tv__empty)
        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener(listener)
        recyclerView = findViewById(R.id.recycler_view)
        recyclerView.setLayoutManager(LinearLayoutManager(this@NoteListActivity))
        notes = ArrayList<Note>()
        notesAdapter = NotesAdapter(
            notes,
            this@NoteListActivity
        )
        recyclerView.setAdapter(notesAdapter)
        optionDialog = getItemOptionBuilder()
    }

    private val listener =
        View.OnClickListener {
            startActivity(
                Intent(
                    this@NoteListActivity,
                    AddNoteActivity::class.java
                )
            )
        }

    override fun onNoteClick(pos: Int) {
        this.pos = pos
        optionDialog.show()
    }

    private fun getItemOptionBuilder(): AlertDialog {
        return AlertDialog.Builder(this@NoteListActivity)
            .setTitle("Select Options")
            .setItems(
                arrayOf("Delete", "Update")
            ) { _: DialogInterface?, i: Int ->
                when (i) {
                    0 -> {
                        noteDatabase.getNoteDao().deleteNote(notes[pos])
                        notes.removeAt(pos)
                        listVisibility()
                    }
                    1 -> startActivityForResult(
                        Intent(
                            this@NoteListActivity,
                            AddNoteActivity::class.java
                        ).putExtra("note", notes[pos]),
                        100
                    )
                }
            }.create()
    }

    private fun updateList(noteList: List<Note>) {
        if (noteList.isNotEmpty()) {
            notes.clear()
            notes.addAll(noteList)
            // hides empty text view
            textViewMsg.visibility = View.GONE
            notesAdapter.notifyDataSetChanged()
        }
    }

    private fun listVisibility() {
        var emptyMsgVisibility = View.GONE
        if (notes.isEmpty()) { // no item to display
            if (textViewMsg.visibility == View.GONE) emptyMsgVisibility =
                View.VISIBLE
        }
        textViewMsg.visibility = emptyMsgVisibility
        notesAdapter.notifyDataSetChanged()
    }

    override fun onDestroy() {
        noteDatabase.cleanUp()
        if (optionDialog.isShowing) {
            optionDialog.dismiss()
        }
        super.onDestroy()
    }
}