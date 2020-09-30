package com.appdevpwl.spacex.data.capsules


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName

@Entity(tableName = "capsule_table")
data class Capsule(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ids")

    val ids: Long,

    @SerializedName("capsule_id")
    @ColumnInfo(name = "ida")
    val capsule_id: String?,
    @SerializedName("capsule_serial")
    @ColumnInfo(name = "capsule_serial")
    val capsule_serial: String?,
    @ColumnInfo(name = "details")
    @SerializedName("details")
    val details: String?,
    @ColumnInfo(name = "landings")
    @SerializedName("landings")
    val landings: Int?,
    @SerializedName("missions")
    @TypeConverters(MissionTypeConverter::class)
    @ColumnInfo(name = "missions")
    val missions: MutableList<Mission>?,
    @ColumnInfo(name = "original_launch")
    @SerializedName("original_launch")
    val original_launch: String?,
    @ColumnInfo(name = "original_launch_unix")
    @SerializedName("original_launch_unix")
    val original_launch_unix: Int?,
    @ColumnInfo(name = "reuse_count")
    @SerializedName("reuse_count")
    val reuse_count: Int?,
    @SerializedName("status")
    @ColumnInfo(name = "status")
    val status: String?,
    @ColumnInfo(name = "type")
    @SerializedName("type")
    val type: String?
) {
    data class Mission(
        @SerializedName("flight")
        val flight: Int,
        @SerializedName("name")
        val name: String
    )
}


