package com.example.pavneet_singh.room_demo_kotin_mvvm_dagger.ui

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.observe
import com.example.pavneet_singh.room_demo_kotin_mvvm_dagger.R
import com.example.pavneet_singh.room_demo_kotin_mvvm_dagger.databinding.ActivityAddNoteBinding
import com.example.pavneet_singh.room_demo_kotin_mvvm_dagger.notedb.NoteDataBase
import com.example.pavneet_singh.room_demo_kotin_mvvm_dagger.notedb.model.Note
import com.example.pavneet_singh.room_demo_kotin_mvvm_dagger.util.DependenciesProvider
import com.example.pavneet_singh.room_demo_kotin_mvvm_dagger.viewmodels.AddNoteViewModel
import com.google.android.material.textfield.TextInputEditText

/**
 * Created by Pavneet_Singh on 2020-01-25.
 */

class AddNoteActivity : AppCompatActivity() {
    private lateinit var etTitle: TextInputEditText
    private lateinit var etContent: TextInputEditText
    private lateinit var noteDatabase: NoteDataBase
    private lateinit var binding: ActivityAddNoteBinding
    private val addNoteViewModel: AddNoteViewModel by viewModels {
        DependenciesProvider.getAddNoteFactory(this@AddNoteActivity)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_note)
        binding.addNoteViewModel = addNoteViewModel
        binding.lifecycleOwner = this
        initUI()
    }

    private fun initUI() {
        etTitle = findViewById(R.id.et_title)
        etContent = findViewById(R.id.et_content)
        noteDatabase = NoteDataBase.getInstance(this@AddNoteActivity)
        val button = findViewById<Button>(R.id.but_save)

        intent.getSerializableExtra("note")?.let {
            (it as Note).apply {
                addNoteViewModel.note = this
                supportActionBar!!.setTitle("Update Note")
                addNoteViewModel.isUpdate = true
            }
        }

        intent.getBooleanExtra("viewNote", false).let {
            if (it) {
                etTitle.isFocusableInTouchMode = false
                etContent.isFocusableInTouchMode = false
                button.visibility = View.GONE
            }
        }

        addNoteViewModel.successEvent.observe(this) {
            if (it > 0) {
                finish()
            } else {
                Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show()
            }
        }
    }
}