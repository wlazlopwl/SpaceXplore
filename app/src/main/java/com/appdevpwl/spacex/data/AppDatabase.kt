package com.appdevpwl.spacex.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.appdevpwl.spacex.data.company.Company
import com.appdevpwl.spacex.data.company.CompanyDao
import com.appdevpwl.spacex.data.Home.HomeDao
import com.appdevpwl.spacex.data.Home.HomeEntity
import com.appdevpwl.spacex.data.capsules.Capsule
import com.appdevpwl.spacex.data.capsules.CapsulesDao
import com.appdevpwl.spacex.data.converters.*
import com.appdevpwl.spacex.data.cores.CoresDao
import com.appdevpwl.spacex.data.cores.CoresItem
import com.appdevpwl.spacex.data.launches.LaunchesDao
import com.appdevpwl.spacex.data.launches.model.LaunchesItem
import com.appdevpwl.spacex.data.rocket.RocketDao
import com.appdevpwl.spacex.data.rocket.model.Rocket


@Database(entities = [HomeEntity::class, Capsule::class, Rocket::class, LaunchesItem::class, CoresItem::class, Company::class],
    version = 6,
    exportSchema = false)
@TypeConverters(
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
    CoreListConverter::class,
    HeadquartersConverter::class,
    CompanyLinksConverter::class

)

abstract class AppDatabase : RoomDatabase() {
    abstract fun testEntityDao(): HomeDao
    abstract fun capsuleDao(): CapsulesDao
    abstract fun rocketDao(): RocketDao
    abstract fun launchesDao(): LaunchesDao
    abstract fun coresDao(): CoresDao
    abstract fun companyDao(): CompanyDao

}