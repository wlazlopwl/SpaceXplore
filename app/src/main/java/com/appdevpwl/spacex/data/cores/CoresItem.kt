package com.appdevpwl.spacex.data.cores

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.appdevpwl.spacex.data.converters.JsonToStringConverter
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Entity(tableName = "cores_table")
@Parcelize
data class CoresItem(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ids")
    val ids: Long,
    val asds_attempts: Int,
    val asds_landings: Int,
    val block: Int?,
    val id: String,
    val last_update: String?,
    @TypeConverters(JsonToStringConverter::class)
    val launches: @RawValue List<String>,
    val reuse_count: Int,
    val rtls_attempts: Int,
    val rtls_landings: Int,
    val serial: String,
    val status: String,
) : Parcelable