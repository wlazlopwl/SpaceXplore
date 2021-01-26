package com.appdevpwl.spacex.data

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import com.appdevpwl.spacex.util.getCurrentMillisTime
import kotlinx.coroutines.flow.first
import javax.inject.Inject


class DataStorePreferences @Inject constructor(private val preferences: DataStore<androidx.datastore.preferences.core.Preferences>) {


    suspend fun saveCurrentUpdateTime(key: String, millis: Long){
        val dataStoreKey = longPreferencesKey(key)
        preferences.edit { datePreferences ->
            datePreferences[dataStoreKey] =millis
        }
    }
    suspend fun getLastUpdateTime(key: String) : Long? {
        val dataStoreKey = longPreferencesKey(key)
        var value = preferences.data.first()
        if (value[dataStoreKey]==null) {
            //TODO not current/ set past because must fetch api when last uptade is null
            saveCurrentUpdateTime(key, getCurrentMillisTime())
            value = preferences.data.first()
        }
        Log.d("lastupdate",value[dataStoreKey].toString())
        return value[dataStoreKey]
    }

    suspend fun saveMaxMinutesBeforeFetchAPI(key: String, minutes:Long){
        val dataStoreKey = longPreferencesKey(key)
        preferences.edit {
            it[dataStoreKey] = minutes
        }
    }

    suspend fun getMaxMinutesBeforeFetchAPI(key: String): Long?{
        val dataStoreKey = longPreferencesKey(key)
        var value = preferences.data.first()
        if (value[dataStoreKey]==null) {
            saveMaxMinutesBeforeFetchAPI(key,5)
            value = preferences.data.first()
        }
        Log.d("maxminutes",value[dataStoreKey].toString())
        return value[dataStoreKey]
    }

}