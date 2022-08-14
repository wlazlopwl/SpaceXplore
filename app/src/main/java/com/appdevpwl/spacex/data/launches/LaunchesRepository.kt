package com.appdevpwl.spacex.data.launches

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.appdevpwl.spacex.data.DataStorePreferences
import com.appdevpwl.spacex.data.Service
import com.appdevpwl.spacex.data.launches.model.LaunchesItem
import com.appdevpwl.spacex.util.Constant
import com.appdevpwl.spacex.util.Constant.Companion.LAUNCHES_LAST_DATE
import com.appdevpwl.spacex.util.deviceIsOnline
import com.appdevpwl.spacex.util.getCurrentMillisTime
import javax.inject.Inject

class LaunchesRepository @Inject constructor(
    private val launchesDao: LaunchesDao,
    private val service: Service,
    private val context: Context,
    private val preferences: DataStorePreferences,
) {

    val snackbarText = MutableLiveData<String>()
    val isLoading = MutableLiveData<Boolean>()

    suspend fun fetchDataAndSaveToDb() {

        when (deviceIsOnline(context)) {
            false -> {
                snackbarText.postValue(Constant.NO_CONNECTION_MESSAGE)
                getUpcomingLaunchesFromDb()
                getPastLaunchesFromDb()
            }
            true -> {
                isLoading.postValue(true)
                val response = service.getAllLaunches()
                if (response.isSuccessful) {
                    response.body().let {
                        if (it != null) {
                            saveLaunchesToDb(it)
                            preferences.saveCurrentUpdateTime(LAUNCHES_LAST_DATE,
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

    private suspend fun saveLaunchesToDb(list: List<LaunchesItem>) {
        launchesDao.replaceAllLaunches(list)
    }

    fun getUpcomingLaunchesFromDb(): LiveData<List<LaunchesItem>> {
        return launchesDao.getUpcomingLaunches()
    }

    fun getPastLaunchesFromDb(): LiveData<List<LaunchesItem>> {
        return launchesDao.getPastLaunches()
    }

    fun getDbSize(): Int {
        return launchesDao.getSize()
    }
}