package com.example.pavneet_singh.room_demo_kotin_mvvm_dagger.notedb.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.pavneet_singh.room_demo_kotin_mvvm_dagger.notedb.model.Note
import com.example.pavneet_singh.room_demo_kotin_mvvm_dagger.util.Constants

/**
 * Created by Pavneet_Singh on 2020-01-25.
 */

@Dao
interface NoteDao {

    /**
     * Room will take care of managing execution on background thread with LiveData
     * Therefore no need of suspend and coroutines
     */
    @Query("SELECT * FROM " + Constants.TABLE_NAME_NOTE)
    fun getNotes(): LiveData<List<Note>>

    /*
     * Insert the object in database
     * @param note, object to be inserted
     */
    @Insert
    suspend fun insertNote(note: Note): Long

    /*
     * update the object in database
     * @param note, object to be updated
     */
    @Update
    suspend fun updateNote(repos: Note): Int

    /*
     * delete the object from database
     * @param note, object to be deleted
     */
    @Delete
    suspend fun deleteNote(note: Note): Int

    // Note... is varargs, here note is an array
/*
     * delete list of objects from database
     * @param note, array of oject to be deleted
     */
    @Delete
    suspend fun deleteNotes(vararg note: Note): Int
}
