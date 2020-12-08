package com.appdevpwl.spacex.data.converters

import androidx.room.TypeConverter
import com.appdevpwl.spacex.data.launches.model.LaunchSite
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class LaunchSiteConverter {

    private val gson = Gson()
    private val type = object : TypeToken<LaunchSite>() {}.type
    @TypeConverter
    fun launchSiteToString(launchSite: LaunchSite): String?{
        return gson.toJson(launchSite, type)
    }
    @TypeConverter
    fun stringToLaunchSite(string: String): LaunchSite?{
        return gson.fromJson(string,type)
    }
}