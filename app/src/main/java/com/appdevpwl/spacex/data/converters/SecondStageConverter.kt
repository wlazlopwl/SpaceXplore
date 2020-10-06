package com.appdevpwl.spacex.data.converters

import androidx.room.TypeConverter
import com.appdevpwl.spacex.data.rocket.SecondStage
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class SecondStageConverter {
    private val gson = Gson()
    private val type = object : TypeToken<SecondStage>() {}.type
    @TypeConverter
    fun secondStageToString(secondStage: SecondStage): String?{
        return gson.toJson(secondStage, type)
    }
    @TypeConverter
    fun stringToSecondStage(string: String): SecondStage?{
        return gson.fromJson(string,type)
    }
}