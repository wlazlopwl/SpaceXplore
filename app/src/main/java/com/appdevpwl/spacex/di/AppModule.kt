package com.appdevpwl.spacex.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.datastore.core.DataStore
import androidx.datastore.createDataStore
import androidx.datastore.preferences.createDataStore
import androidx.preference.Preference
import androidx.preference.PreferenceDataStore
import androidx.preference.PreferenceManager
import androidx.room.Room
import com.appdevpwl.spacex.data.AppDatabase
import com.appdevpwl.spacex.data.DataStorePreferences
import com.appdevpwl.spacex.data.capsules.CapsulesDao
import dagger.Module
import dagger.Provides
import java.util.prefs.Preferences
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
    fun provideCoreDao(appDatabase: AppDatabase)= appDatabase.coresDao()

    @Singleton
    @Provides
    fun providelaunchesDao(appDatabase: AppDatabase)= appDatabase.launchesDao()

    @Singleton
    @Provides
    fun provideCompanyDao(appDatabase: AppDatabase)= appDatabase.companyDao()

    @Singleton
    @Provides
    fun provideSharedPreferences(context: Context) : SharedPreferences =
        PreferenceManager.getDefaultSharedPreferences(context)


    @Singleton
    @Provides
    fun provideDataStore( context: Context) : DataStore<androidx.datastore.preferences.core.Preferences> {
        return context.createDataStore(
            name="dataStore"
        )
    }
}