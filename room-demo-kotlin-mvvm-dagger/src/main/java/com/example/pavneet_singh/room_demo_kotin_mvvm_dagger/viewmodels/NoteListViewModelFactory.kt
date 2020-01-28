package com.example.pavneet_singh.room_demo_kotin_mvvm_dagger.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pavneet_singh.room_demo_kotin_mvvm_dagger.notedb.NoteDataBase

/**
 * Created by Pavneet_Singh on 2020-01-27.
 */

class NoteListViewModelFactory(private val noteDatabase: NoteDataBase) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return NoteListViewModel(noteDatabase) as T
    }
}