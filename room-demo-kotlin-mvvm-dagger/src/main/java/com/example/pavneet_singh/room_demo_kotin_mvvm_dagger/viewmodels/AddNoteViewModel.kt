package com.example.pavneet_singh.room_demo_kotin_mvvm_dagger.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pavneet_singh.room_demo_kotin_mvvm_dagger.notedb.NoteDataBase
import com.example.pavneet_singh.room_demo_kotin_mvvm_dagger.notedb.model.Note
import kotlinx.coroutines.launch

/**
 * Created by Pavneet_Singh on 2020-01-28.
 */

class AddNoteViewModel(private val noteDatabase: NoteDataBase) : ViewModel() {
    private val _finishActivityEvent = MutableLiveData<Long>()

    val successEvent: LiveData<Long>
        get() = _finishActivityEvent

    var isUpdate = false

    // empty node object, values for insert and update will be updated using data binding
    var note: Note = Note(content = "", title = "")

    private fun updateNote() {
        viewModelScope.launch {
            val rowsEffected = noteDatabase.getNoteDao().updateNote(note)
            _finishActivityEvent.value = rowsEffected.toLong()
        }
    }

    private fun insertNote() {
        viewModelScope.launch {
            val rowID = noteDatabase.getNoteDao().insertNote(note)
            _finishActivityEvent.value = rowID
        }
    }

    fun processNote() {
        if (isUpdate) {
            updateNote()
        } else {
            insertNote()
        }
    }

    fun getActionButtonText() = if(isUpdate) "Update" else "Save"

}