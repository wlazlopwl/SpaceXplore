package com.appdevpwl.spacex.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.appdevpwl.spacex.util.Constant.Companion.LENGTH_UNIT
import com.appdevpwl.spacex.util.Constant.Companion.MASS_UNIT
import kotlinx.coroutines.flow.Flow
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
        val value = preferences.data.map { currentPreferences ->
            currentPreferences[dataStoreKey] ?: 1511823542403

        }
        return value.first()
    }

    suspend fun saveMassUnit(key: String, unit: String) {
        val dataStoreKey = stringPreferencesKey(key)
        preferences.edit {
            it[dataStoreKey] = unit
        }
    }

    val massUnit: Flow<String> = preferences.data.map {
        val dataStoreKey = stringPreferencesKey(MASS_UNIT)
        it[dataStoreKey] ?: "kg"
    }

    suspend fun saveLengthUnit(key: String, unit: String) {
        val dataStoreKey = stringPreferencesKey(key)
        preferences.edit {
            it[dataStoreKey] = unit
        }
    }

    val lengthUnit: Flow<String> = preferences.data.map {
        val dataStoreKey = stringPreferencesKey(LENGTH_UNIT)
        it[dataStoreKey] ?: "meters"
    }
}