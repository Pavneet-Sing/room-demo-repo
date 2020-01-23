package com.example.pavneet_singh.room_demo_rxandroid.notedb.dao;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.pavneet_singh.room_demo_rxandroid.notedb.model.Note;
import com.example.pavneet_singh.room_demo_rxandroid.util.Constants;

import java.util.List;

import io.reactivex.Maybe;
import io.reactivex.Single;

/**
 * Created by Pavneet_Singh on 12/31/17.
 */

@Dao
public interface NoteDao {

    @Query("SELECT * FROM " + Constants.TABLE_NAME_NOTE)
    LiveData<List<Note>> getNotes();

    /*
     * Insert the object in database
     * @param note, object to be inserted
     */
    @Insert
    Single<Long> insertNote(Note note);

    /*
     * update the object in database
     * @param note, object to be updated
     */
    @Update
    void updateNote(Note repos);

    /*
     * delete the object from database
     * @param note, object to be deleted
     */
    @Delete
    void deleteNote(Note note);

    // Note... is varargs, here note is an array
    /*
     * delete list of objects from database
     * @param note, array of oject to be deleted
     */
    @Delete
    void deleteNotes(Note... note);

}
