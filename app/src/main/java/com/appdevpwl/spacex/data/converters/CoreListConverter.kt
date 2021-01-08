package com.appdevpwl.spacex.data.converters

import androidx.room.TypeConverter
import com.appdevpwl.spacex.data.launches.model.Core
import com.appdevpwl.spacex.data.launches.model.Failure
import com.appdevpwl.spacex.data.rocket.model.PayloadWeight
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*

class CoreListConverter  {

     private val gson= Gson()
    @TypeConverter
    fun coreToString(core: List<Core>): String?{
        return  gson.toJson(core)
    }

    @TypeConverter
    fun stringToCore(string: String?) : List<Core>{
        if (string == null) {
            return Collections.emptyList()
        }

        val type = object : TypeToken<List<Core>>() {
        }.type

        return gson.fromJson(string,type)
    }

}