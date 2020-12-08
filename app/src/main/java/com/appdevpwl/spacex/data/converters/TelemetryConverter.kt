package com.appdevpwl.spacex.data.converters

import androidx.room.TypeConverter
import com.appdevpwl.spacex.data.launches.model.LaunchFailureDetails
import com.appdevpwl.spacex.data.launches.model.LaunchSite
import com.appdevpwl.spacex.data.launches.model.Telemetry
import com.appdevpwl.spacex.data.launches.model.Timeline
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class TelemetryConverter {

    private val gson = Gson()
    private val type = object : TypeToken<Telemetry>() {}.type
    @TypeConverter
    fun telemetryDetailsToString(telemetry: Telemetry): String?{
        return gson.toJson(telemetry, type)
    }
    @TypeConverter
    fun stringToTelemetry(string: String): Telemetry?{
        return gson.fromJson(string,type)
    }
}