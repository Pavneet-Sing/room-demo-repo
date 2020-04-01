package com.example.pavneet_singh.room_demo_kotin_mvvm_dagger.di.activities

import com.example.pavneet_singh.room_demo_kotin_mvvm_dagger.adapter.NotesAdapter
import com.example.pavneet_singh.room_demo_kotin_mvvm_dagger.di.ActivityScope
import com.example.pavneet_singh.room_demo_kotin_mvvm_dagger.notedb.model.Note
import com.example.pavneet_singh.room_demo_kotin_mvvm_dagger.ui.NoteListActivity
import dagger.Module
import dagger.Provides

/**
 * Created by Pavneet_Singh on 2020-01-31.
 */

@Module
abstract class NoteActivityModule {

    // Now dagger 2.26 support non-module annotation companion object
    // https://github.com/google/dagger/releases/tag/dagger-2.26
    companion object {
        @ActivityScope
        @Provides
        fun getMutableList() = mutableListOf<Note>()

        @ActivityScope
        @Provides
        fun getNotesAdapter(noteListActivity: NoteListActivity, notes: MutableList<Note>) =
                NotesAdapter(notes, noteListActivity)
    }

}