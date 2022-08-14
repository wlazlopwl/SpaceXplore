package com.appdevpwl.spacex.util

import android.annotation.SuppressLint
import java.util.*

@SuppressLint("SimpleDateFormat")
fun convertUnixTime(unixTime: Int): String {

    val longUnixTime: Long = unixTime.toLong()
    val sdf = java.text.SimpleDateFormat("yyyy-MM-dd HH:mm")
    val date = Date(longUnixTime * 1000)
    return sdf.format(date).toString()


}