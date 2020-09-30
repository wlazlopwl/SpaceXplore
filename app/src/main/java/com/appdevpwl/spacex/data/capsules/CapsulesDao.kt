package com.appdevpwl.spacex.data.capsules

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CapsulesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllCapsules(capsules: List<Capsule>)

    @Query("DELETE FROM capsule_table")
    suspend fun deleteAllCapsules()

    @Query("SELECT COUNT(capsule_serial) FROM capsule_table")
    fun countCapsules(): Int

    @Query("SELECT * FROM(capsule_table)")
    fun loadAllCapsules(): LiveData<List<Capsule>>


}