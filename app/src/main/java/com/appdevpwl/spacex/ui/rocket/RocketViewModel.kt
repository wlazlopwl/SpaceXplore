package com.appdevpwl.spacex.ui.rocket

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appdevpwl.spacex.data.DataStorePreferences
import com.appdevpwl.spacex.data.rocket.RocketRepository
import com.appdevpwl.spacex.data.rocket.model.Rocket
import com.appdevpwl.spacex.util.Constant.Companion.MAX_TIME_TO_FETCH_MILLIS
import com.appdevpwl.spacex.util.Constant.Companion.ROCKET_LAST_DATE
import com.appdevpwl.spacex.util.compareMillis
import com.appdevpwl.spacex.util.getCurrentMillisTime
import kotlinx.coroutines.launch
import javax.inject.Inject

class RocketViewModel @Inject constructor(
    private val rocketRepository: RocketRepository,
    private val preferences: DataStorePreferences,
) : ViewModel() {

    val rocketLiveData: MutableLiveData<List<Rocket>> = rocketRepository.rocketLiveData
    val snackbarText: LiveData<String> = rocketRepository.snackbarText
    val loadingData: LiveData<Boolean> = rocketRepository.isLoading


    init {
        getRocketData()
    }

    fun getRocketData() {
        viewModelScope.launch {
            when (rocketRepository.getDbSize()) {
                0 -> refreshData()
                else -> {
                    val oldTime = preferences.getLastUpdateTime(ROCKET_LAST_DATE)
                    val timeToFetch: Long? =
                        preferences.getMaxMinutesBeforeFetchAPI(MAX_TIME_TO_FETCH_MILLIS)
                    val currentTime = getCurrentMillisTime()
                    when (compareMillis(oldTime!!, currentTime, timeToFetch!!)) {
                        true -> refreshData()
                        else -> rocketRepository.getAllRocketsFromDb()
                    }
                }


            }
        }
    }

    fun refreshData() {
        viewModelScope.launch {
            rocketRepository.fetchDataAndSaveToDb()

        }

    }


}
