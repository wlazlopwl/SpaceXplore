package com.appdevpwl.spacex.data.rocket.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.appdevpwl.spacex.data.converters.*

@Entity(tableName = "rocket_table")
data class Rocket(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ids")
    val ids: Long,
    val active: Boolean,
    val boosters: Int,
    val company: String,
    val cost_per_launch: Int,
    val country: String,
    val description: String,
    @TypeConverters(DiameterConverter::class)
    val diameter: Diameter,
    @TypeConverters(EnginesConverter::class)
    val engines: Engines,
    val first_flight: String,
    @TypeConverters(FirstStageConverter::class)
    val first_stage: FirstStage,
    @TypeConverters(JsonToStringConverter::class)
    val flickr_images: List<String>,
    @TypeConverters(HeightConverter::class)
    val height: Height,
    val id: String,
    @TypeConverters(LandingLegsConverter::class)
    val landing_legs: LandingLegs,
    @TypeConverters(MassConverter::class)
    val mass: Mass,
    val name: String,
    @TypeConverters(PayloadWeightTypeConverter::class)
    val payload_weights: List<PayloadWeight>,
    @TypeConverters(SecondStageConverter::class)
    val second_stage: SecondStage,
    val stages: Int,
    val success_rate_pct: Int,
    val type: String,
    val wikipedia: String,
)