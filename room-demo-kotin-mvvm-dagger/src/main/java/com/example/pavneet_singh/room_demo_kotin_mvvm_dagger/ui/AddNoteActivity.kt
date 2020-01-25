package com.example.pavneet_singh.room_demo_kotin_mvvm_dagger.ui

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.pavneet_singh.room_demo_kotin_mvvm_dagger.R
import com.example.pavneet_singh.room_demo_kotin_mvvm_dagger.notedb.NoteDataBase
import com.example.pavneet_singh.room_demo_kotin_mvvm_dagger.notedb.model.Note
import com.google.android.material.textfield.TextInputEditText
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by Pavneet_Singh on 2020-01-25.
 */

class AddNoteActivity : AppCompatActivity() {
    private lateinit var et_title: TextInputEditText
    private lateinit var et_content: TextInputEditText
    private lateinit var noteDatabase: NoteDataBase
    private lateinit var note: Note
    private var update = false
    private lateinit var compositeDisposable: CompositeDisposable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)
        initUI()
    }

    private fun initUI() {
        compositeDisposable = CompositeDisposable()
        et_title = findViewById(R.id.et_title)
        et_content = findViewById(R.id.et_content)
        noteDatabase = NoteDataBase.getInstance(this@AddNoteActivity)
        val button = findViewById<Button>(R.id.but_save)

        intent.getSerializableExtra("note")?.let {
            (it as Note).apply {
                note = this
                supportActionBar!!.setTitle("Update Note")
                update = true
                button.text = "Update"
                et_title.setText(title)
                et_content.setText(content)
            }
        }
        button.setOnClickListener {
            if (update) {
                note.content = et_content.getText().toString()
                note.title = et_title.getText().toString()
                compositeDisposable.add(noteDatabase.getNoteDao().updateNote(note)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                        { finish() },
                        { e ->
                            Toast.makeText(
                                this,
                                "Update failed due to " + e.message,
                                Toast.LENGTH_SHORT
                            ).show()
                            e.printStackTrace()
                        }
                    ))
            } else {
                note = Note(
                    content = et_content.text.toString(), // using placeholder name to avoid passing long id, as using default as 0
                    title = et_title.text.toString()
                )
                compositeDisposable.add(noteDatabase.getNoteDao().insertNote(note)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                        { rowId ->
                            note.note_id = rowId // update rowId in database
                            finish()
                        },
                        { obj: Throwable -> obj.printStackTrace() }
                    ))
            }
        }
    }

    override fun onDestroy() {
        compositeDisposable.dispose()
        super.onDestroy()
    }
}