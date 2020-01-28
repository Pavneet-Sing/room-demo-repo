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
    private lateinit var etTitle: TextInputEditText
    private lateinit var etContent: TextInputEditText
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
        etTitle = findViewById(R.id.et_title)
        etContent = findViewById(R.id.et_content)
        noteDatabase = NoteDataBase.getInstance(this@AddNoteActivity)
        val button = findViewById<Button>(R.id.but_save)

        intent.getSerializableExtra("note")?.let {
            (it as Note).apply {
                note = this
                supportActionBar!!.title = "Update Note"
                update = true
                button.text = "Update"
                etTitle.setText(title)
                etContent.setText(content)
            }
        }
        button.setOnClickListener {
            if (update) {
                note.content = etContent.text.toString()
                note.title = etTitle.text.toString()
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
                    content = etContent.text.toString(), // using placeholder name to avoid passing long id, as using default as 0
                    title = etTitle.text.toString()
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