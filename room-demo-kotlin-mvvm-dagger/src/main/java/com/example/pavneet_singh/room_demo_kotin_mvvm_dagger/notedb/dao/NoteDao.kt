package com.example.pavneet_singh.room_demo_kotin_mvvm_dagger.notedb.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.pavneet_singh.room_demo_kotin_mvvm_dagger.notedb.model.Note
import com.example.pavneet_singh.room_demo_kotin_mvvm_dagger.util.Constants
import io.reactivex.Completable
import io.reactivex.Single

/**
 * Created by Pavneet_Singh on 2020-01-25.
 */

@Dao
interface NoteDao {
    @Query("SELECT * FROM " + Constants.TABLE_NAME_NOTE)
    fun getNotes(): LiveData<List<Note>>

    /*
     * Insert the object in database
     * @param note, object to be inserted
     */
    @Insert
    fun insertNote(note: Note): Single<Long>

    /*
     * update the object in database
     * @param note, object to be updated
     */
    @Update
    fun updateNote(repos: Note): Completable

    /*
     * delete the object from database
     * @param note, object to be deleted
     */
    @Delete
    fun deleteNote(note: Note): Completable

    // Note... is varargs, here note is an array
/*
     * delete list of objects from database
     * @param note, array of oject to be deleted
     */
    @Delete
    fun deleteNotes(vararg note: Note)
}
