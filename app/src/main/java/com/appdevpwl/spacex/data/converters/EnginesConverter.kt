package com.appdevpwl.spacex.data.converters

import androidx.room.TypeConverter
import com.appdevpwl.spacex.data.rocket.Engines
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class EnginesConverter {
    private val gson = Gson()
    private val type = object : TypeToken<Engines>() {}.type
    @TypeConverter
    fun enginesToString(engines: Engines): String?{
        return gson.toJson(engines, type)
    }
    @TypeConverter
    fun stringToEngines(string: String): Engines?{
        return gson.fromJson(string,type)
    }
}