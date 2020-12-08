package com.appdevpwl.spacex.data.launches.model

data class LaunchFailureDetails(
    val altitude: Int,
    val reason: String,
    val time: Int
)