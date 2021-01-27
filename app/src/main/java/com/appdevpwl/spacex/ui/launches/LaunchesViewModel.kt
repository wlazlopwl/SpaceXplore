package com.appdevpwl.spacex.ui.launches

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appdevpwl.spacex.data.DataStorePreferences
import com.appdevpwl.spacex.data.launches.LaunchesRepository
import com.appdevpwl.spacex.data.launches.model.LaunchesItem
import com.appdevpwl.spacex.util.Constant
import com.appdevpwl.spacex.util.Constant.Companion.LAUNCHES_LAST_DATE
import com.appdevpwl.spacex.util.compareMillis
import com.appdevpwl.spacex.util.getCurrentMillisTime
import kotlinx.coroutines.launch
import javax.inject.Inject

class LaunchesViewModel @Inject constructor(
    private val launchesRepository: LaunchesRepository,
    private val preferences: DataStorePreferences,
) : ViewModel() {


    val snackbarText: LiveData<String> = launchesRepository.snackbarText
    val loadingData: LiveData<Boolean> = launchesRepository.isLoading

    val upcomingLaunchesLiveData: MutableLiveData<List<LaunchesItem>> =
        launchesRepository.upcomingLaunchesLiveData
    val pastLaunchesLiveData: MutableLiveData<List<LaunchesItem>> =
        launchesRepository.pastLaunchesLiveData

    init {
        getLaunchesData()
        getUpcomingLaunches()
        getPastLaunches()
    }

    fun getAllLaunches() {
        viewModelScope.launch {
            launchesRepository.getAllLaunchesFromDb()
        }
    }

    fun getUpcomingLaunches() {
        viewModelScope.launch {
            launchesRepository.getUpcomingLaunchesFromDb()
        }
    }

    fun getPastLaunches() {
        viewModelScope.launch {
            launchesRepository.getPastLaunchesFromDb()
        }
    }

    fun getLaunchesData() {
        viewModelScope.launch {
            when (launchesRepository.getDbSize()) {
                0 -> refreshData()
                else -> {
                    val oldTime = preferences.getLastUpdateTime(LAUNCHES_LAST_DATE)
                    val timeToFetch: Long? =
                        preferences.getMaxMinutesBeforeFetchAPI(Constant.MAX_TIME_TO_FETCH_MILLIS)
                    val currentTime = getCurrentMillisTime()
                    when (compareMillis(oldTime!!, currentTime, timeToFetch!!)) {
                        true -> refreshData()
                        else -> launchesRepository.getUpcomingLaunchesFromDb()
                    }
                }
            }
        }
    }

    fun refreshData() {
        viewModelScope.launch {
            launchesRepository.fetchDataAndSaveToDb()

        }
    }

}