package com.appdevpwl.spacex.data.launches.model

data class Fairings(
    val recovered: Boolean,
    val recovery_attempt: Boolean,
    val reused: Boolean,
    val ships: List<String>,
)