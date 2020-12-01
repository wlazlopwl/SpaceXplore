package com.appdevpwl.spacex.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import androidx.room.Room
import com.appdevpwl.spacex.data.AppDatabase
import com.appdevpwl.spacex.data.capsules.CapsulesDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [ ActivityBuildersModule::class, ViewModelModule::class])
class AppModule {
    @Singleton
    @Provides
    fun provideContext( application: Application): Context{
        return application.applicationContext
    }
    @Singleton
    @Provides
    fun providesRoomDB(context: Context) : AppDatabase{
        return Room.databaseBuilder(context, AppDatabase::class.java,"db").allowMainThreadQueries().fallbackToDestructiveMigration().build()
    }

    @Singleton
    @Provides
    fun providesDao(appDatabase: AppDatabase) = appDatabase.testEntityDao()

    @Singleton
    @Provides
    fun provideCapsuleDao(appDatabase: AppDatabase)= appDatabase.capsuleDao()

    @Singleton
    @Provides
    fun provideRocketDao(appDatabase: AppDatabase)= appDatabase.rocketDao()

    @Singleton
    @Provides
    fun provideSharedPreferences(context: Context) : SharedPreferences =
        PreferenceManager.getDefaultSharedPreferences(context)
//        context.getSharedPreferences("SharedPreferences", Context.MODE_PRIVATE)


}