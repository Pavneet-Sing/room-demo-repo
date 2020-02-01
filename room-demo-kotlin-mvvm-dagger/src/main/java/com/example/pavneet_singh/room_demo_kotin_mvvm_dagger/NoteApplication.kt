package com.example.pavneet_singh.room_demo_kotin_mvvm_dagger

import com.example.pavneet_singh.room_demo_kotin_mvvm_dagger.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

/**
 * Created by Pavneet_Singh on 2020-01-30.
 */

class NoteApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().application(this).build()
    }

}