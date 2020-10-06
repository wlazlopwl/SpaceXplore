package com.appdevpwl.spacex.data.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*

class JsonToStringConverter {

    private val gson = Gson()

    @TypeConverter
    fun stringToJson(string: String): MutableList<String> {
        val type = object : TypeToken<MutableList<String>>(){}.type

        if (string == null) {
            return Collections.emptyList()
        }
        return gson.fromJson(string, type)
    }
    @TypeConverter
    fun JsonToString(json :MutableList<String>) : String{
        return  gson.toJson(json)
    }
}