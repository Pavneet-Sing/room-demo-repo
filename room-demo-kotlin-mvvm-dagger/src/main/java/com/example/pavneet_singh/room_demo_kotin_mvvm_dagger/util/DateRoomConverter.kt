package com.example.pavneet_singh.room_demo_kotin_mvvm_dagger.util

import androidx.room.TypeConverter
import java.util.*

/**
 * Created by Pavneet_Singh on 2020-01-25.
 */

object DateRoomConverter {
    @TypeConverter
    @JvmStatic
    fun toDate(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    @JvmStatic
    fun toLong(value: Date?): Long? {
        return value?.time
    }
}