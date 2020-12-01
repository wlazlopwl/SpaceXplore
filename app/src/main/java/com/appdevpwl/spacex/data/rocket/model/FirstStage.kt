package com.appdevpwl.spacex.data.rocket.model

data class FirstStage(
    val burn_time_sec: Int?,
    val engines: Int?,
    val fuel_amount_tons: Double?,
    val reusable: Boolean?,
    val thrust_sea_level: ThrustSeaLevelX?,
    val thrust_vacuum: ThrustVacuumX?
)