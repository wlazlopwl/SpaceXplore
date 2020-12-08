package com.appdevpwl.spacex.data.converters

import androidx.room.TypeConverter
import com.appdevpwl.spacex.data.launches.model.LaunchFailureDetails
import com.appdevpwl.spacex.data.launches.model.LaunchSite
import com.appdevpwl.spacex.data.launches.model.Timeline
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class TimelineConverter {

    private val gson = Gson()
    private val type = object : TypeToken<Timeline>() {}.type
    @TypeConverter
    fun timelineDetailsToString(timeline: Timeline?): String?{
        return gson.toJson(timeline, type)
    }
    @TypeConverter
    fun stringToTimeline(string: String): Timeline?{
        return gson.fromJson(string,type)
    }
}