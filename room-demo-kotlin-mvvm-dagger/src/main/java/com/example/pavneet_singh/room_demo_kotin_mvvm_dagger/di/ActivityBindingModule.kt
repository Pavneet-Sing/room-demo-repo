package com.example.pavneet_singh.room_demo_kotin_mvvm_dagger.di

import com.example.pavneet_singh.room_demo_kotin_mvvm_dagger.di.activities.AddNoteActivityModule
import com.example.pavneet_singh.room_demo_kotin_mvvm_dagger.di.activities.NoteActivityModule
import com.example.pavneet_singh.room_demo_kotin_mvvm_dagger.ui.AddNoteActivity
import com.example.pavneet_singh.room_demo_kotin_mvvm_dagger.ui.NoteListActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by Pavneet_Singh on 2020-01-30.
 */

@Module
abstract class ActivityBindingModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [NoteActivityModule::class])
    abstract fun injectIntoNoteListActivity(): NoteListActivity


    //AddNoteActivityModule can be removed as it's empty
    @ActivityScope
    @ContributesAndroidInjector(modules = [AddNoteActivityModule::class])
    abstract fun injectIntoAddNoteActivity(): AddNoteActivity

}