package com.appdevpwl.spacex.util

import java.util.*

fun convertUnixTime(unixTime:Int) : String {

    var longUnixTime: Long = unixTime.toLong()
    val sdf = java.text.SimpleDateFormat("yyyy-MM-dd HH:mm")
    val date = Date(longUnixTime * 1000)
    return sdf.format(date).toString()


}