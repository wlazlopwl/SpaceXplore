package com.appdevpwl.spacex.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TestEntityDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(testEntity: TestEntity)

    @Query("SELECT * from TestEntity")
    fun getAllTest():List<TestEntity>
}