package com.example.pavneet_singh.room_demo_rxandroid.util;

import androidx.room.TypeConverter;

import java.util.Date;

/**
 * Created by Pavneet_Singh on 12/31/17.
 */

public class DateRoomConverter {

    @TypeConverter
    public static Date toDate(Long value) {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public static Long toLong(Date value) {
        return value == null ? null : value.getTime();
    }
}
