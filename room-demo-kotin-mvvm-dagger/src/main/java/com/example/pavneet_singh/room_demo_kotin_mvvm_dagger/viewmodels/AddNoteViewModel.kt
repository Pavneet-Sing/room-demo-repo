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

class AddNoteViewModel(val noteDatabase: NoteDataBase) : ViewModel() {
    val updateLiveData = MutableLiveData<Int>()
    val insertLiveData = MutableLiveData<Long>()

    fun updateNote(note: Note): LiveData<Int>{
        viewModelScope.launch {
            val rowsEffected = noteDatabase.getNoteDao().updateNote(note)
            updateLiveData.value = rowsEffected
        }
        return updateLiveData
    }

    fun insertNote(note: Note): LiveData<Long>{
        viewModelScope.launch {
            val rowsID = noteDatabase.getNoteDao().insertNote(note)
            insertLiveData.value = rowsID
        }
        return insertLiveData
    }
}