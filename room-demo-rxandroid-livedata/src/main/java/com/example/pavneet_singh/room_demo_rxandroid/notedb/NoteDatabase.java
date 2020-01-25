package com.example.pavneet_singh.room_demo_rxandroid.notedb;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.pavneet_singh.room_demo_rxandroid.notedb.dao.NoteDao;
import com.example.pavneet_singh.room_demo_rxandroid.notedb.model.Note;
import com.example.pavneet_singh.room_demo_rxandroid.util.Constants;
import com.example.pavneet_singh.room_demo_rxandroid.util.DateRoomConverter;

/**
 * Created by Pavneet_Singh on 12/31/17.
 */

@Database(entities = {Note.class}, version = 1)
@TypeConverters({DateRoomConverter.class})
public abstract class NoteDatabase extends RoomDatabase {

    public abstract NoteDao getNoteDao();


    private static NoteDatabase noteDB;

    // synchronized is use to avoid concurrent access in multithred environment
    public static /*synchronized*/ NoteDatabase getInstance(Context context) {
        if (null == noteDB) {
            synchronized (NoteDatabase.class) { // dual lock
                if (null == noteDB) {
                    noteDB = buildDatabaseInstance(context);
                }
            }
        }
        return noteDB;
    }

    private static NoteDatabase buildDatabaseInstance(Context context) {
        return Room.databaseBuilder(context,
                NoteDatabase.class,
                Constants.DB_NAME).build();
    }

    public void cleanUp() {
        noteDB = null;
    }
}