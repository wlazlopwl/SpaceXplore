package com.appdevpwl.spacex.data.cores

import androidx.lifecycle.LiveData
import androidx.room.*
import com.appdevpwl.spacex.data.launches.model.LaunchesItem

@Dao
interface CoresDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllCores(cores: List<CoresItem>)

    @Transaction
    suspend fun replaceAllCores(cores: List<CoresItem>) {
        deleteAllCores()
        insertAllCores(cores)
    }

    @Query("DELETE FROM cores_table")
    suspend fun deleteAllCores()

    @Query("SELECT * FROM cores_table")
    suspend fun getAllCores():List<CoresItem>

    @Query("SELECT * FROM cores_table where launches LIKE '%' || :id || '%'")
    suspend fun getAllCoresByLaunchesId(id: String): List<CoresItem>

    @Query("SELECT COUNT(id) FROM cores_table")
     fun getSize(): Int
}