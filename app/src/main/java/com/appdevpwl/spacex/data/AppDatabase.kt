package com.appdevpwl.spacex.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.appdevpwl.spacex.data.capsules.Capsule
import com.appdevpwl.spacex.data.capsules.CapsulesDao
import com.appdevpwl.spacex.data.capsules.MissionTypeConverter


@Database(entities = [TestEntity::class, Capsule::class], version = 2, exportSchema = false)
@TypeConverters(MissionTypeConverter::class)
abstract class AppDatabase: RoomDatabase() {
    abstract fun testEntityDao(): TestEntityDao
    abstract fun capsuleDao(): CapsulesDao

}