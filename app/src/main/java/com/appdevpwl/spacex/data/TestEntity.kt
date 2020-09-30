package com.appdevpwl.spacex.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TestEntity(
    @PrimaryKey(autoGenerate = true) var id: Int = 1,
    var testName: String?
)