package com.appdevpwl.spacex.data.converters

import androidx.room.TypeConverter
import com.appdevpwl.spacex.data.launches.model.Failure
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*

class FailureListConverter {

    private val gson = Gson()

    @TypeConverter
    fun failureToString(failure: List<Failure>): String? {
        return gson.toJson(failure)
    }

    @TypeConverter
    fun stringToFailure(string: String?): List<Failure> {
        if (string == null) {
            return Collections.emptyList()
        }

        val type = object : TypeToken<List<Failure>>() {
        }.type

        return gson.fromJson(string, type)
    }
}