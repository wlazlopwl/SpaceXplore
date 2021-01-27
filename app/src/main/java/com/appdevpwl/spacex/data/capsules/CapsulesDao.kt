package com.appdevpwl.spacex.data.capsules

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CapsulesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllCapsules(capsules: List<Capsule>)

    @Transaction
    suspend fun replaceAllCapsules(capsules: List<Capsule>) {
        deleteAllCapsules()
        insertAllCapsules(capsules)
    }

    @Query("DELETE FROM capsule_table")
    suspend fun deleteAllCapsules()

    @Query("SELECT COUNT(id) FROM capsule_table")
    fun getSize(): Int

    @Query("SELECT * FROM capsule_table")
    fun getAllCapsules(): List<Capsule>

    @Query("SELECT * FROM capsule_table ORDER BY serial DESC")
    fun getAllCapsulesSortByTypeDescending(): LiveData<List<Capsule>>

    @Query("SELECT * FROM capsule_table ORDER BY serial ASC")
    fun getAllCapsulesSortByTypeAscending(): LiveData<List<Capsule>>

    @Query("SELECT * FROM capsule_table ORDER BY serial ASC")
    fun getAllCapsulesSortByLaunchTimeAscending(): LiveData<List<Capsule>>

    @Query("SELECT * FROM capsule_table ORDER BY serial DESC")
    fun getAllCapsulesSortByLaunchTimeDescending(): LiveData<List<Capsule>>


}