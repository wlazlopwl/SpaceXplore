package com.appdevpwl.spacex.data.converters

import androidx.room.TypeConverter
import com.appdevpwl.spacex.data.company.Headquarters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class HeadquartersConverter {

    private val gson = Gson()
    private val type = object : TypeToken<Headquarters>() {}.type
    @TypeConverter
    fun headquartersToString(headquarters: Headquarters): String?{
        return gson.toJson(headquarters, type)
    }
    @TypeConverter
    fun stringToHeadquarters(string: String): Headquarters?{
        return gson.fromJson(string,type)
    }
}