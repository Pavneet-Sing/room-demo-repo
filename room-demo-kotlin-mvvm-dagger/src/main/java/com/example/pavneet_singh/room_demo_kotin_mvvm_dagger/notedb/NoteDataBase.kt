package com.example.pavneet_singh.room_demo_kotin_mvvm_dagger.notedb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.pavneet_singh.room_demo_kotin_mvvm_dagger.notedb.dao.NoteDao
import com.example.pavneet_singh.room_demo_kotin_mvvm_dagger.notedb.model.Note
import com.example.pavneet_singh.room_demo_kotin_mvvm_dagger.util.Constants
import com.example.pavneet_singh.room_demo_kotin_mvvm_dagger.util.DateRoomConverter

/**
 * Created by Pavneet_Singh on 2020-01-25.
 */

@Database(
    entities = [Note::class],
    version = 1
)
@TypeConverters(
    DateRoomConverter::class
)
abstract class NoteDataBase : RoomDatabase() {
    abstract fun getNoteDao(): NoteDao

    companion object {
        private var noteDB: NoteDataBase? = null
        // synchronized is use to avoid concurrent access in multithred environment
        @JvmStatic
        fun  /*synchronized*/getInstance(context: Context): NoteDataBase {
            if (null == noteDB) {
                synchronized(NoteDataBase::class) {
                    // dual lock
                    if (null == noteDB) {
                        noteDB = buildDatabaseInstance(context)
                    }
                }
            }
            return noteDB!!
        }

        @JvmStatic
        private fun buildDatabaseInstance(context: Context): NoteDataBase {
            return Room.databaseBuilder(
                context,
                NoteDataBase::class.java,
                Constants.DB_NAME
            ).build()
        }
    }
}