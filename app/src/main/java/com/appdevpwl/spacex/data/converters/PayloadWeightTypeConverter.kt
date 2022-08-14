package com.appdevpwl.spacex.data.converters

import androidx.room.TypeConverter
import com.appdevpwl.spacex.data.rocket.model.PayloadWeight
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*

class PayloadWeightTypeConverter {

    private val gson = Gson()

    @TypeConverter
    fun payloadWeightToString(payloadWeight: List<PayloadWeight>): String? {
        return gson.toJson(payloadWeight)
    }

    @TypeConverter
    fun stringToPayloadWeight(string: String?): List<PayloadWeight> {
        if (string == null) {
            return Collections.emptyList()
        }

        val type = object : TypeToken<List<PayloadWeight>>() {
        }.type

        return gson.fromJson(string, type)
    }
}