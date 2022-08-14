package com.appdevpwl.spacex.data.launches

import androidx.lifecycle.LiveData
import androidx.room.*
import com.appdevpwl.spacex.data.launches.model.LaunchesItem

@Dao
interface LaunchesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllLaunches(launches: List<LaunchesItem>)

    @Transaction
    suspend fun replaceAllLaunches(launches: List<LaunchesItem>) {
        deleteAllLaunches()
        insertAllLaunches(launches)
    }

    @Query("DELETE FROM launches_table")
    suspend fun deleteAllLaunches()

    @Query("SELECT * FROM launches_table where upcoming = :upcoming ORDER BY flight_number ASC")
    fun getUpcomingLaunches(upcoming: Boolean = true): LiveData<List<LaunchesItem>>

    @Query("SELECT * FROM launches_table where upcoming = :upcoming")
    fun getPastLaunches(upcoming: Boolean = false): LiveData<List<LaunchesItem>>

    @Query("SELECT * FROM launches_table where ids=:id")
    fun getPastLaunches(id: Int): LaunchesItem

    @Query("SELECT COUNT(id) FROM launches_table")
    fun getSize(): Int
}
