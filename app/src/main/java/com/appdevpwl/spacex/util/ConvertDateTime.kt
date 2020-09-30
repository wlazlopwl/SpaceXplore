package com.appdevpwl.spacex.util

import java.util.*

fun convertUnixTime(unixTime:Int) : String {

    var longUnixxTime: Long = unixTime.toLong()
    val sdf = java.text.SimpleDateFormat("yyyy-MM-dd HH:mm")
    val date = java.util.Date(longUnixxTime * 1000)
    return sdf.format(date).toString()


}