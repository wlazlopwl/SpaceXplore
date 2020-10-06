package com.appdevpwl.spacex.data.converters

import androidx.room.TypeConverter
import com.appdevpwl.spacex.data.rocket.Height
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class HeightConverter {
    private val gson = Gson()
    private val type = object : TypeToken<Height>() {}.type

    @TypeConverter
    fun heightToString(height: Height): String? {
        return gson.toJson(height, type)
    }

    @TypeConverter
    fun stringToHeight(string: String): Height? {
        return gson.fromJson(string, type)
    }
}