package com.appdevpwl.spacex.data.capsules

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*

@Dao
interface CapsulesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllCapsules(capsules: List<Capsule>)
    @Transaction
    suspend fun replaceAllCapsules(capsules: List<Capsule>){
        deleteAllCapsules()
        insertAllCapsules(capsules)
    }

    @Query("DELETE FROM capsule_table")
    suspend fun deleteAllCapsules()

    @Query("SELECT COUNT(serial) FROM capsule_table")
    fun countCapsules(): Int

    @Query("SELECT * FROM capsule_table")
    fun loadAllCapsules(): LiveData<List<Capsule>>

    @Query("SELECT * FROM capsule_table ORDER BY serial DESC")
    fun getAllCapsulesSortByTypeDescending(): LiveData<List<Capsule>>

    @Query("SELECT * FROM capsule_table ORDER BY serial ASC")
    fun getAllCapsulesSortByTypeAscending(): LiveData<List<Capsule>>

    @Query("SELECT * FROM capsule_table ORDER BY serial ASC")
    fun getAllCapsulesSortByLaunchTimeAscending(): LiveData<List<Capsule>>

    @Query("SELECT * FROM capsule_table ORDER BY serial DESC")
    fun getAllCapsulesSortByLaunchTimeDescending(): LiveData<List<Capsule>>


}