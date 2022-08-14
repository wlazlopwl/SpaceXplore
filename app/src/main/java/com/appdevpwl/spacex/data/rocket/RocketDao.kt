package com.appdevpwl.spacex.data.rocket

import androidx.lifecycle.LiveData
import androidx.room.*
import com.appdevpwl.spacex.data.rocket.model.Rocket

@Dao
interface RocketDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllRocket(rocket: List<Rocket>)

    @Transaction
    suspend fun replaceAllRockets(rocket: List<Rocket>) {
        deleteAllRockets()
        insertAllRocket(rocket)
    }

    @Query("DELETE FROM rocket_table")
    suspend fun deleteAllRockets()

    @Query("SELECT * FROM rocket_table")
    fun getAllRockets(): LiveData<List<Rocket>>

    @Query("SELECT * FROM rocket_table where id=:id")
    fun getRocketByRocketId(id: String): LiveData<Rocket>

    @Query("SELECT COUNT(id) FROM rocket_table")
    fun getSize(): Int
}
