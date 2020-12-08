package com.appdevpwl.spacex.data.converters

import androidx.room.TypeConverter
import com.appdevpwl.spacex.data.launches.model.LaunchFailureDetails
import com.appdevpwl.spacex.data.launches.model.LaunchSite
import com.appdevpwl.spacex.data.launches.model.Rocket
import com.appdevpwl.spacex.data.launches.model.Timeline
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class RocketConverter {

    private val gson = Gson()
    private val type = object : TypeToken<Rocket>() {}.type
    @TypeConverter
    fun rocketToString(rocket: Rocket): String?{
        return gson.toJson(rocket, type)
    }
    @TypeConverter
    fun stringToRocket(string: String): Rocket?{
        return gson.fromJson(string,type)
    }
}