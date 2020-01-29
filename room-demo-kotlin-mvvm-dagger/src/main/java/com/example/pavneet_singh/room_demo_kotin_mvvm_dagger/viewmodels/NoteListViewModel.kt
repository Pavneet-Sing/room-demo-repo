package com.example.pavneet_singh.room_demo_kotin_mvvm_dagger.viewmodels

import androidx.annotation.UiThread
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.*
import com.example.pavneet_singh.room_demo_kotin_mvvm_dagger.notedb.NoteDataBase
import com.example.pavneet_singh.room_demo_kotin_mvvm_dagger.notedb.model.Note
import kotlinx.coroutines.launch

/**
 * Created by Pavneet_Singh on 2020-01-27.
 */

class NoteListViewModel(private val noteDatabase: NoteDataBase) : ViewModel() {

    private val numOfRowsDeleted = MutableLiveData<Int>()
    val isListEmpty = ObservableBoolean(true)

    val notesLiveData: LiveData<List<Note>> = liveData {
        emitSource(noteDatabase.getNoteDao().getNotes())
    }

    @UiThread
    fun deleteNote(note: Note): LiveData<Int> {
        viewModelScope.launch {
            numOfRowsDeleted.value = noteDatabase.getNoteDao().deleteNote(note)
        }
        return numOfRowsDeleted
    }

}