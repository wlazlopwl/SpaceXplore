package com.appdevpwl.spacex.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface HomeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(homeEntity: HomeEntity)

    @Query("SELECT * from HomeEntity")
    fun getAllTest():List<HomeEntity>
}