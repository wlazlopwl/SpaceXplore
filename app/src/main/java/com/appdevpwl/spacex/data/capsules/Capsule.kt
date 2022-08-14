package com.appdevpwl.spacex.data.capsules

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.appdevpwl.spacex.data.converters.JsonToStringConverter

@Entity(tableName = "capsule_table")
data class Capsule(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ids")
    val ids: Long,
    val id: String,
    val land_landings: Int,
    val last_update: String?,
    @TypeConverters(JsonToStringConverter::class)
    val launches: List<String>,
    val reuse_count: Int,
    val serial: String,
    val status: String,
    val type: String,
    val water_landings: Int,
)