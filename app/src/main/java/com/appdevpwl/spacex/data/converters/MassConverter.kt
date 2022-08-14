package com.appdevpwl.spacex.data.converters

import androidx.room.TypeConverter
import com.appdevpwl.spacex.data.rocket.model.Mass
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MassConverter {
    private val gson = Gson()
    private val type = object : TypeToken<Mass>() {}.type

    @TypeConverter
    fun massToString(mass: Mass): String? {
        return gson.toJson(mass, type)
    }

    @TypeConverter
    fun stringToMass(string: String): Mass? {
        return gson.fromJson(string, type)
    }
}