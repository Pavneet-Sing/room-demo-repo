package com.example.pavneet_singh.room_demo_kotin_mvvm_dagger.di.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pavneet_singh.room_demo_kotin_mvvm_dagger.viewmodels.AddNoteViewModel
import com.example.pavneet_singh.room_demo_kotin_mvvm_dagger.viewmodels.NoteListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Created by Pavneet_Singh on 2020-01-31.
 */

/**
 * Add all the view-model dependencies here
 *
 * Note:
 * ViewModelModule to keep the single instance of ViewModelFactory
 * Can keep viewmodel map entries in separate module though can result in unnecessary factory objects
 */
@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(NoteListViewModel::class)
    abstract fun bindNoteListViewModel(mainViewModel: NoteListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AddNoteViewModel::class)
    abstract fun bindAddNoteViewModel(addNoteViewModel: AddNoteViewModel): ViewModel

}