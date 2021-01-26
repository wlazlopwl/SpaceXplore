package com.appdevpwl.spacex.util

import android.util.Log
import java.util.*

fun getCurrentMillisTime(): Long {
    val date = Date(System.currentTimeMillis())
    return date.time
}

fun compareMillis(oldMillis: Long, currentMillis: Long, timeToFetch: Long): Boolean {

    val maxNotFetchFromApi: Long = 1000 * 60 * timeToFetch
    val differenceOfTime = currentMillis - oldMillis
    Log.d("difference", differenceOfTime.toString())
    return when {
        differenceOfTime > maxNotFetchFromApi -> true
        else -> false
    }


}

fun millisToDate(millis: Long): Date{
    return Date(millis)

}

