package com.appdevpwl.spacex.data.rocket

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.appdevpwl.spacex.data.DataStorePreferences
import com.appdevpwl.spacex.data.Service
import com.appdevpwl.spacex.data.rocket.model.Rocket
import com.appdevpwl.spacex.util.Constant.Companion.NO_CONNECTION_MESSAGE
import com.appdevpwl.spacex.util.Constant.Companion.ROCKET_LAST_DATE
import com.appdevpwl.spacex.util.deviceIsOnline
import com.appdevpwl.spacex.util.getCurrentMillisTime
import javax.inject.Inject

class RocketRepository @Inject constructor(
    private val rocketDao: RocketDao,
    private val service: Service,
    private val context: Context,
    private val preferences: DataStorePreferences,
) {
    val snackbarText = MutableLiveData<String>()
    val isLoading = MutableLiveData<Boolean>()

    suspend fun fetchDataAndSaveToDb() {

        when (deviceIsOnline(context)) {
            false -> {
                snackbarText.postValue(NO_CONNECTION_MESSAGE)
                getAllRocketsFromDb()
            }
            true -> {
                isLoading.postValue(true)
                val response = service.getRockets()
                if (response.isSuccessful) {
                    response.body().let {
                        if (it != null) {
                            saveRocketsToDb(it)
                            preferences.saveCurrentUpdateTime(ROCKET_LAST_DATE,
                                getCurrentMillisTime())
                        }
                    }
                } else {
                    snackbarText.postValue(response.errorBody().toString())
                }
            }
        }
        isLoading.postValue(false)
    }

    private suspend fun saveRocketsToDb(list: List<Rocket>) {
        rocketDao.replaceAllRockets(list)
    }

    fun getRocketById(id: String): LiveData<Rocket> {
        return rocketDao.getRocketByRocketId(id)
    }

    fun getDbSize(): Int {
        return rocketDao.getSize()
    }

    fun getAllRocketsFromDb(): LiveData<List<Rocket>> {
        return rocketDao.getAllRockets()
    }
}