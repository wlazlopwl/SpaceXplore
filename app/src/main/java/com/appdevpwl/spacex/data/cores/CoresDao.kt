package com.appdevpwl.spacex.data.cores

import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.coroutines.flow.Flow

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
    fun getAllCores(): LiveData<List<CoresItem>>

    @Query("SELECT * FROM cores_table where launches LIKE '%' || :id || '%'")
    fun getAllCoresByLaunchesId(id: String): LiveData<List<CoresItem>>

    @Query("SELECT * FROM cores_table where serial LIKE '%' || :query || '%'")
    fun searchCores(query: String): LiveData<List<CoresItem>>

    @Query("SELECT COUNT(id) FROM cores_table")
    fun getSize(): Int

}