package com.example.pavneet_singh.room_demo_kotin_mvvm_dagger.notedb.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.pavneet_singh.room_demo_kotin_mvvm_dagger.util.Constants
import java.io.Serializable
import java.util.*

/**
 * Created by Pavneet_Singh on 2020-01-25.
 */

@Entity(tableName = Constants.TABLE_NAME_NOTE)
data class Note(
    @PrimaryKey(autoGenerate = true)
    var note_id: Long = 0,

    // column name will be "note_content" instead of "content" in table
    @ColumnInfo(name = "note_content")
    var content: String,

    var title: String,
    val date: Date = Date(System.currentTimeMillis())
) : Serializable