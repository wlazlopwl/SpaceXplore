package com.appdevpwl.spacex.data.launches

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.appdevpwl.spacex.data.DataStorePreferences
import com.appdevpwl.spacex.data.Service
import com.appdevpwl.spacex.data.launches.model.LaunchesItem
import com.appdevpwl.spacex.util.Constant
import com.appdevpwl.spacex.util.Constant.Companion.LAUNCHES_LAST_DATE
import com.appdevpwl.spacex.util.deviceIsOnline
import com.appdevpwl.spacex.util.getCurrentMillisTime
import com.appdevpwl.spacex.util.millisToDate

import javax.inject.Inject

class LaunchesRepository @Inject constructor(
    private val launchesDao: LaunchesDao,
    private val service: Service,
    private val context: Context,
    private val preferences: DataStorePreferences,
) {

    val snackbarText = MutableLiveData<String>()
    val isLoading = MutableLiveData<Boolean>()

    val launchesLiveData = MutableLiveData<List<LaunchesItem>>()
    val upcomingLaunchesLiveData = MutableLiveData<List<LaunchesItem>>()
    val pastLaunchesLiveData = MutableLiveData<List<LaunchesItem>>()

    suspend fun fetchDataAndSaveToDb() {

        when (deviceIsOnline(context)) {

            false -> {
                snackbarText.postValue(Constant.NO_CONNECTION_MESSAGE)
                getAllLaunchesFromDb()
            }
            true -> {
                isLoading.postValue(true)
                val response = service.getAllLaunches()
                if (response.isSuccessful) {
                    val body = response.body()
                    response.body().let {
                        if (it != null) {
                            saveLaunchesToDb(it)
                            preferences.saveCurrentUpdateTime(LAUNCHES_LAST_DATE,
                                getCurrentMillisTime())
                        }
                        launchesLiveData.value = body!!
                    }
                } else {
                    snackbarText.postValue(response.errorBody().toString())
                }
                isLoading.postValue(false)
            }
        }
    }

    private suspend fun saveLaunchesToDb(list: List<LaunchesItem>) {
        launchesDao.replaceAllLaunches(list)
    }


    suspend fun getAllLaunchesFromDb() {
        launchesLiveData.value = launchesDao.getAllLaunches()
    }

    suspend fun getUpcomingLaunchesFromDb() {
        isLoading.postValue(true)

        upcomingLaunchesLiveData.value = launchesDao.getUpcomingLaunches()
        isLoading.postValue(false)
        snackbarText.postValue(millisToDate(preferences.getLastUpdateTime(LAUNCHES_LAST_DATE)!!).toString())
    }

    fun getPastLaunchesFromDb() {
        isLoading.postValue(true)

        pastLaunchesLiveData.value = launchesDao.getPastLaunches()
        isLoading.postValue(false)
    }
    fun getDbSize(): Int {
        return launchesDao.getSize()
    }


}