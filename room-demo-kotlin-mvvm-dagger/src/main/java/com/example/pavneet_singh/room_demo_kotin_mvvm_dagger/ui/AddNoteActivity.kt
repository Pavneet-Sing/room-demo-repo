package com.example.pavneet_singh.room_demo_kotin_mvvm_dagger.ui

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import com.example.pavneet_singh.room_demo_kotin_mvvm_dagger.R
import com.example.pavneet_singh.room_demo_kotin_mvvm_dagger.notedb.NoteDataBase
import com.example.pavneet_singh.room_demo_kotin_mvvm_dagger.notedb.model.Note
import com.example.pavneet_singh.room_demo_kotin_mvvm_dagger.util.DependenciesProvider
import com.example.pavneet_singh.room_demo_kotin_mvvm_dagger.viewmodels.AddNoteViewModel
import com.google.android.material.textfield.TextInputEditText

/**
 * Created by Pavneet_Singh on 2020-01-25.
 */

class AddNoteActivity : AppCompatActivity() {
    private lateinit var et_title: TextInputEditText
    private lateinit var et_content: TextInputEditText
    private lateinit var noteDatabase: NoteDataBase
    private lateinit var note: Note
    private var update = false
    private val addNoteViewModel: AddNoteViewModel by viewModels {
        DependenciesProvider.getAddNoteFactory(this@AddNoteActivity)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)
        initUI()
    }

    private fun initUI() {
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
        intent.getBooleanExtra("view", false).let {
            if (it){
                et_title.isEnabled = false
                et_content.isEnabled = false
                button.visibility = View.GONE
            }
        }
        button.setOnClickListener {
            if (update) {
                note.content = et_content.getText().toString()
                note.title = et_title.getText().toString()
                addNoteViewModel.updateNote(note).observe(this) {
                    if (it > 0) {
                        finish()
                    } else {
                        Toast.makeText(this, "Update failed", Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                note = Note(
                    content = et_content.text.toString(), // using placeholder name to avoid passing long id, as using default as 0
                    title = et_title.text.toString()
                )

                addNoteViewModel.insertNote(note).observe(this) {
                    if (it > 0) {
                        finish()
                    } else {
                        Toast.makeText(this, "Insertion failure", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}