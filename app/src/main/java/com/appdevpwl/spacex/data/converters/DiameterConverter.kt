package com.appdevpwl.spacex.data.converters

import androidx.room.TypeConverter
import com.appdevpwl.spacex.data.rocket.model.Diameter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class DiameterConverter {

    private val gson = Gson()
    private val type = object : TypeToken<Diameter>() {}.type
    @TypeConverter
    fun diameterToString(diameter: Diameter): String?{
        return gson.toJson(diameter, type)
    }
    @TypeConverter
    fun stringToDiameter(string: String): Diameter?{
        return gson.fromJson(string,type)
    }
}