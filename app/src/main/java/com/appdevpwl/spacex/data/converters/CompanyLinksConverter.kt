package com.appdevpwl.spacex.data.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CompanyLinksConverter {

    private val gson = Gson()
    private val type = object : TypeToken<com.appdevpwl.spacex.data.company.Links>() {}.type

    @TypeConverter
    fun linksToString(links: com.appdevpwl.spacex.data.company.Links): String? {
        return gson.toJson(links, type)
    }

    @TypeConverter
    fun stringToLinks(string: String): com.appdevpwl.spacex.data.company.Links? {
        return gson.fromJson(string, type)
    }
}