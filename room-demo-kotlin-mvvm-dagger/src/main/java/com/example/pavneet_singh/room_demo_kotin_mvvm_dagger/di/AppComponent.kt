package com.example.pavneet_singh.room_demo_kotin_mvvm_dagger.di

import android.app.Application
import com.example.pavneet_singh.room_demo_kotin_mvvm_dagger.NoteApplication
import com.example.pavneet_singh.room_demo_kotin_mvvm_dagger.di.database.DataBaseModule
import com.example.pavneet_singh.room_demo_kotin_mvvm_dagger.di.viewmodel.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton


@Singleton
@Component(
        modules = [
            AndroidInjectionModule::class,
            ActivityBindingModule::class,
            DataBaseModule::class,
            ViewModelModule::class
        ]
)
interface AppComponent : AndroidInjector<NoteApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

}