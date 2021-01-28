package com.appdevpwl.spacex.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class DataStorePreferences @Inject constructor(private val preferences: DataStore<androidx.datastore.preferences.core.Preferences>) {


    suspend fun saveCurrentUpdateTime(key: String, millis: Long) {
        val dataStoreKey = longPreferencesKey(key)
        preferences.edit { datePreferences ->
            datePreferences[dataStoreKey] = millis
        }
    }

    suspend fun getLastUpdateTime(key: String): Long {
        val dataStoreKey = longPreferencesKey(key)
        val value = preferences.data.map { currentPreferences ->
            currentPreferences[dataStoreKey] ?: 1511823542403

        }
        return value.first()
    }

    suspend fun saveMaxMinutesBeforeFetchAPI(key: String, minutes: Long) {
        val dataStoreKey = longPreferencesKey(key)
        preferences.edit {
            it[dataStoreKey] = minutes
        }
    }

    suspend fun getMaxMinutesBeforeFetchAPI(key: String): Long? {
        val dataStoreKey = longPreferencesKey(key)
        var value = preferences.data.first()
        if (value[dataStoreKey] == null) {
            saveMaxMinutesBeforeFetchAPI(key, 5)
            value = preferences.data.first()
        }
        return value[dataStoreKey]
    }

}