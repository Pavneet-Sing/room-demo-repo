package com.example.pavneet_singh.room_demo_kotin_mvvm_dagger.util

import android.content.Context
import com.example.pavneet_singh.room_demo_kotin_mvvm_dagger.notedb.NoteDataBase
import com.example.pavneet_singh.room_demo_kotin_mvvm_dagger.viewmodels.AddNoteViewModelFactory
import com.example.pavneet_singh.room_demo_kotin_mvvm_dagger.viewmodels.NoteListViewModelFactory

/**
 * Created by Pavneet_Singh on 2020-01-27.
 */

object DependenciesProvider{

    private fun getNoteDatabase(context: Context) = NoteDataBase.getInstance(context)

    fun getNoteListFactory(context: Context) =
        NoteListViewModelFactory(
            getNoteDatabase(context)
        )

    fun getAddNoteFactory(context: Context) =
        AddNoteViewModelFactory(
            getNoteDatabase(context)
        )

}