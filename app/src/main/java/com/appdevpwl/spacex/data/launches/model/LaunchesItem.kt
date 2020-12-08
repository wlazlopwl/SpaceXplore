package com.appdevpwl.spacex.data.launches.model

import androidx.room.*
import com.appdevpwl.spacex.data.converters.*

@Entity(tableName = "launches_table")
data class LaunchesItem(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long,
    val details: String?,
    val flight_number: Int?,
    val is_tentative: Boolean?,
    val launch_date_local: String?,
    val launch_date_unix: Int?,
    val launch_date_utc: String?,
    @TypeConverters(LaunchFailureDetailsConverter::class)
    val launch_failure_details: LaunchFailureDetails?,
    @TypeConverters(LaunchSiteConverter::class)
    val launch_site: LaunchSite?,
    val launch_success: Boolean?,
    val launch_window: Int?,
    val launch_year: String?,
    @TypeConverters(LinksConverter::class)
    val links: Links?,
    val mission_id: List<String>?,
    val mission_name: String?,
    @TypeConverters(RocketConverter::class)
    val rocket: Rocket?,
    val ships: List<String>?,
    val static_fire_date_unix: Int?,
    val static_fire_date_utc: String?,
    val tbd: Boolean?,
    @TypeConverters(TelemetryConverter::class)
    val telemetry: Telemetry?,
    val tentative_max_precision: String?,
    @TypeConverters(TimelineConverter::class)
    val timeline: Timeline?,
    val upcoming: Boolean?
)