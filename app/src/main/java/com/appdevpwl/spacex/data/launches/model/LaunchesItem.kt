package com.appdevpwl.spacex.data.launches.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.appdevpwl.spacex.data.converters.CoreListConverter
import com.appdevpwl.spacex.data.converters.FailureListConverter
import com.appdevpwl.spacex.data.converters.FairingsConverter
import com.appdevpwl.spacex.data.converters.LinksConverter
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Entity(tableName = "launches_table")
@Parcelize
data class LaunchesItem(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ids")
    val ids: Int,

    val auto_update: Boolean?,
    val capsules: List<String>?,
    @TypeConverters(CoreListConverter::class)
    val cores: @RawValue List<Core>?,
    val crew: List<String>?,
    val date_local: String?,
    val date_precision: String?,
    val date_unix: Int?,
    val date_utc: String?,
    val details: String?,
    @TypeConverters(FailureListConverter::class)
    val failures: @RawValue List<Failure>?,
    @TypeConverters(FairingsConverter::class)
    val fairings: @RawValue Fairings?,
    val flight_number: Int?,
    val id: String?,
    val launchpad: String?,
    @TypeConverters(LinksConverter::class)
    val links: @RawValue Links?,
    val name: String?,
    val net: Boolean?,
    val payloads: List<String>?,
    val rocket: String?,
    val ships: List<String>?,
    val static_fire_date_unix: Int?,
    val static_fire_date_utc: String?,
    val success: Boolean?,
    val tbd: Boolean?,
    val upcoming: Boolean?,
    val window: Int?,
) : Parcelable