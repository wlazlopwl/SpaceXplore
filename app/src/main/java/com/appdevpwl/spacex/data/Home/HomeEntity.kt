package com.appdevpwl.spacex.data.Home

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class HomeEntity(
    @PrimaryKey(autoGenerate = true) var id: Int = 1,
    var testName: String?
)