package com.appdevpwl.spacex.ui.cores

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appdevpwl.spacex.data.DataStorePreferences
import com.appdevpwl.spacex.data.cores.CoresItem
import com.appdevpwl.spacex.data.cores.CoresRepository
import com.appdevpwl.spacex.util.Constant.Companion.CORES_LAST_DATE
import com.appdevpwl.spacex.util.Constant.Companion.MAX_TIME_TO_FETCH_MILLIS
import com.appdevpwl.spacex.util.compareMillis
import com.appdevpwl.spacex.util.getCurrentMillisTime
import kotlinx.coroutines.launch
import javax.inject.Inject

class CoresViewModel @Inject constructor(
    private val coresRepository: CoresRepository,
    private val preferences: DataStorePreferences,
) :
    ViewModel() {


    val coresLiveData: LiveData<List<CoresItem>> = coresRepository.getAllCoresFromDb()
    lateinit var liveDataCoresByLaunchesId: LiveData<List<CoresItem>>
    val snackbarText: LiveData<String> = coresRepository.snackbarText
    val loadingData: LiveData<Boolean> = coresRepository.isLoading


    init {
        getData()
    }


    private fun getData() {
        viewModelScope.launch {
            when (coresRepository.getDbSize()) {
                0 -> refreshData()
                else -> {
                    val oldTime = preferences.getLastUpdateTime(CORES_LAST_DATE)
                    val timeToFetch: Long? =
                        preferences.getMaxMinutesBeforeFetchAPI(MAX_TIME_TO_FETCH_MILLIS)
                    val currentTime = getCurrentMillisTime()
                    when (compareMillis(oldTime, currentTime, timeToFetch!!)) {
                        true -> refreshData()
                        else -> coresRepository.getAllCoresFromDb()
                    }
                }
            }
        }
    }


    fun refreshData() {
        viewModelScope.launch {
            coresRepository.fetchDataAndSaveToDb()
        }

    }

    fun getAllCoresByLaunchesId(id: String) {
        viewModelScope.launch {
            liveDataCoresByLaunchesId = coresRepository.getAllCoresByLaunchesId(id)
        }

    }


    fun searchBySerial(newText: String): LiveData<List<CoresItem>> {
        return coresRepository.searchBySerial(newText)
    }
}
