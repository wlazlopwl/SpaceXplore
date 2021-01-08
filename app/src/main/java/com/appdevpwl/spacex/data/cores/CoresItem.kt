package com.appdevpwl.spacex.data.cores

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cores_table")
data class CoresItem(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ids")
    val ids:Long,
    val asds_attempts: Int,
    val asds_landings: Int,
    val block: Int?,
    val id: String,
    val last_update: String?,
    val launches: List<String>,
    val reuse_count: Int,
    val rtls_attempts: Int,
    val rtls_landings: Int,
    val serial: String,
    val status: String
)