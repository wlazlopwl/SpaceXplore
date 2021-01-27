package com.appdevpwl.spacex.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.appdevpwl.spacex.data.capsules.Capsule
import com.appdevpwl.spacex.data.capsules.CapsulesDao
import com.appdevpwl.spacex.data.converters.*
import com.appdevpwl.spacex.data.cores.CoresDao
import com.appdevpwl.spacex.data.cores.CoresItem
import com.appdevpwl.spacex.data.launches.LaunchesDao
import com.appdevpwl.spacex.data.launches.model.LaunchesItem
import com.appdevpwl.spacex.data.rocket.model.Rocket
import com.appdevpwl.spacex.data.rocket.RocketDao


@Database(entities = [TestEntity::class, Capsule::class, Rocket::class, LaunchesItem::class, CoresItem::class], version = 5, exportSchema = false)
@TypeConverters(
//    MissionTypeConverter::class,
    JsonToStringConverter::class,
    PayloadWeightTypeConverter::class,
    DiameterConverter::class,
    FirstStageConverter::class,
    EnginesConverter::class,
    HeightConverter::class,
    LandingLegsConverter::class,
    SecondStageConverter::class,
    MassConverter::class,
    LinksConverter::class,
    FairingsConverter::class,
    FailureListConverter::class,
    CoreListConverter::class

)

abstract class AppDatabase : RoomDatabase() {
    abstract fun testEntityDao(): TestEntityDao
    abstract fun capsuleDao(): CapsulesDao
    abstract fun rocketDao(): RocketDao
    abstract fun launchesDao(): LaunchesDao
    abstract fun coresDao(): CoresDao

}