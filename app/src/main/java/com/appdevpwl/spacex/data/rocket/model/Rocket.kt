package com.appdevpwl.spacex.data.rocket.model

import androidx.room.*
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
    val diameter: Diameter?,
    @TypeConverters(EnginesConverter::class)
    val engines: Engines?,
    val first_flight: String,
    @TypeConverters(FirstStageConverter::class)
    val first_stage: FirstStage?,
    @TypeConverters(HeightConverter::class)
    val height: Height?,
    val id: Int,
    @TypeConverters(LandingLegsConverter::class)
    val landing_legs: LandingLegs?,
    @TypeConverters(MassConverter::class)
    val mass: Mass?,
    @TypeConverters(PayloadWeightTypeConverter::class)
    val payload_weights: List<PayloadWeight>,
    val rocket_id: String,
    val rocket_name: String,
    val rocket_type: String,
    @TypeConverters(SecondStageConverter::class)
    val second_stage: SecondStage?,
    val stages: Int,
    val success_rate_pct: Int,
    val wikipedia: String,
    val flickr_images: List<String>
)