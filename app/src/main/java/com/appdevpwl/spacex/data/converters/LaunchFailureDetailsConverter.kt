package com.appdevpwl.spacex.data.converters

import androidx.room.TypeConverter
import com.appdevpwl.spacex.data.launches.model.LaunchFailureDetails
import com.appdevpwl.spacex.data.launches.model.LaunchSite
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class LaunchFailureDetailsConverter {

    private val gson = Gson()
    private val type = object : TypeToken<LaunchFailureDetails>() {}.type
    @TypeConverter
    fun launchFailureDetailsToString(launchFailureDetails: LaunchFailureDetails?): String?{
        return gson.toJson(launchFailureDetails, type)
    }
    @TypeConverter
    fun stringToLaunchFailureDetails(string: String): LaunchFailureDetails?{
        return gson.fromJson(string,type)
    }
}