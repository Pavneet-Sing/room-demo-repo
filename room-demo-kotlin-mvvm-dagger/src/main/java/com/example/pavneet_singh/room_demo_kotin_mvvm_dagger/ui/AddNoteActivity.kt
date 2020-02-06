package com.example.pavneet_singh.room_demo_kotin_mvvm_dagger.ui

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.example.pavneet_singh.room_demo_kotin_mvvm_dagger.R
import com.example.pavneet_singh.room_demo_kotin_mvvm_dagger.base.BaseActivity
import com.example.pavneet_singh.room_demo_kotin_mvvm_dagger.databinding.ActivityAddNoteBinding
import com.example.pavneet_singh.room_demo_kotin_mvvm_dagger.notedb.model.Note
import com.example.pavneet_singh.room_demo_kotin_mvvm_dagger.viewmodels.AddNoteViewModel
import javax.inject.Inject

/**
 * Created by Pavneet_Singh on 2020-01-25.
 */

class AddNoteActivity : BaseActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var binding: ActivityAddNoteBinding
    private val addNoteViewModel: AddNoteViewModel by viewModels {
        viewModelFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_note)
        binding.addNoteViewModel = addNoteViewModel
        binding.lifecycleOwner = this
        initUI()
    }

    private fun initUI() {
        val button = findViewById<Button>(R.id.but_save)

        intent.getSerializableExtra("note")?.let {
            (it as Note).apply {
                addNoteViewModel.note = this
                supportActionBar?.title = "Update Note"
                addNoteViewModel.isUpdate = true
            }
        }

        intent.getBooleanExtra("viewNote", false).let {
            if (it) {
                binding.etTitle.isFocusableInTouchMode = false
                binding.etContent.isFocusableInTouchMode = false
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