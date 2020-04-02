package com.example.pavneet_singh.room_demo_kotin_mvvm_dagger.di.database

import android.app.Application
import com.example.pavneet_singh.room_demo_kotin_mvvm_dagger.notedb.NoteDataBase
import dagger.Module
import dagger.Provides

/**
 * Created by Pavneet_Singh on 2020-01-30.
 */

@Module
class DataBaseModule {

    @Provides
    fun getRoomDB(context: Application) =
            NoteDataBase.getInstance(
                    context
            )

}