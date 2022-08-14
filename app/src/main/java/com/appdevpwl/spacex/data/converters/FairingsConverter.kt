package com.appdevpwl.spacex.data.converters

import androidx.room.TypeConverter
import com.appdevpwl.spacex.data.launches.model.Fairings
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class FairingsConverter {

    private val gson = Gson()
    private val type = object : TypeToken<Fairings?>() {}.type

    @TypeConverter
    fun fairingsToString(fairings: Fairings?): String? {
        return gson.toJson(fairings, type)
    }

    @TypeConverter
    fun stringToFairings(string: String): Fairings? {
        return gson.fromJson(string, type)
    }
}