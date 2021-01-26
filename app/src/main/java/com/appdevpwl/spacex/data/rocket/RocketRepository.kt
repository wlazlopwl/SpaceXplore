package com.appdevpwl.spacex.data.rocket

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.appdevpwl.spacex.data.DataStorePreferences
import com.appdevpwl.spacex.data.Service
import com.appdevpwl.spacex.data.rocket.model.Rocket
import com.appdevpwl.spacex.util.*
import com.appdevpwl.spacex.util.Constant.Companion.NO_CONNECTION_MESSAGE
import com.appdevpwl.spacex.util.Constant.Companion.ROCKET_LAST_DATE

import javax.inject.Inject

class RocketRepository @Inject constructor(
    private val rocketDao: RocketDao,
    private val service: Service,
    private val context: Context,
    private val preferences: DataStorePreferences
) {

    val snackbarText = MutableLiveData<String>()
    val isLoading = MutableLiveData<Boolean>()
    val rocketLiveData = MutableLiveData<List<Rocket>>()
    val rocketByIdLiveData = MutableLiveData<Rocket>()

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
                    val body = response.body()
                    response.body().let {
                        if (it != null) {
                            saveRocketsToDb(it)
                            preferences.saveCurrentUpdateTime(ROCKET_LAST_DATE,
                                getCurrentMillisTime())
                        }
                        rocketLiveData.value = body!!
                    }
                } else {
                    snackbarText.postValue(response.errorBody().toString())
                }
                isLoading.postValue(false)
            }
        }
    }

    private suspend fun saveRocketsToDb(list: List<Rocket>) {
        rocketDao.replaceAllRockets(list)
    }

    suspend fun getRocketById(id: Int) {
        rocketByIdLiveData.value = rocketDao.getRocketByRocketId(id)
    }

    fun getDbSize(): Int {
        return rocketDao.getSize()
    }

    suspend fun getAllRocketsFromDb() {
        isLoading.postValue(true)

        rocketLiveData.value = rocketDao.getAllRockets()
        isLoading.postValue(false)
        snackbarText.postValue(millisToDate(preferences.getLastUpdateTime(ROCKET_LAST_DATE)!!).toString())
    }


}