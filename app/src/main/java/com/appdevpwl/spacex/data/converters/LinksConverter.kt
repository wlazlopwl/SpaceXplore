package com.appdevpwl.spacex.data.converters

import androidx.room.TypeConverter
import com.appdevpwl.spacex.data.launches.model.LaunchSite
import com.appdevpwl.spacex.data.launches.model.Links
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class LinksConverter {

    private val gson = Gson()
    private val type = object : TypeToken<Links>() {}.type
    @TypeConverter
    fun linksToString(links: Links): String?{
        return gson.toJson(links, type)
    }
    @TypeConverter
    fun stringToLinks(string: String): Links?{
        return gson.fromJson(string,type)
    }
}