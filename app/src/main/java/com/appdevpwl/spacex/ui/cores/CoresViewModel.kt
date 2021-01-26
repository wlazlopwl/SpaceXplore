package com.appdevpwl.spacex.ui.cores

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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
    private val preferences: DataStorePreferences
) :
    ViewModel() {


    val liveData: MutableLiveData<List<CoresItem>> = coresRepository.liveData
    private val _liveDataCoresByLaunchesId = MutableLiveData<List<CoresItem>>()
    val liveDataCoresByLaunchesId: LiveData<List<CoresItem>> = _liveDataCoresByLaunchesId
    val snackbarText: LiveData<String> = coresRepository.snackbarText
    val loadingData: LiveData<Boolean> = coresRepository.isLoading


    init {

        getData()

    }


    fun getData() {
        viewModelScope.launch {
            Log.d("dbSIZE", coresRepository.getDbSize().toString())
            when (coresRepository.getDbSize()) {
                0 -> refreshData()
                else -> {
                    val oldTime = preferences.getLastUpdateTime(CORES_LAST_DATE)
                    val timeToFetch: Long? =
                        preferences.getMaxMinutesBeforeFetchAPI(MAX_TIME_TO_FETCH_MILLIS)
                    val currentTime = getCurrentMillisTime()
                    when (compareMillis(oldTime!!, currentTime, timeToFetch!!)) {
                        true -> refreshData()
                        else -> coresRepository.getAllCoresFromDb()
                    }
                }


            }
        }
    }


    suspend fun refreshData() {
        coresRepository.fetchDataAndSaveToDb()
    }

    fun getAllCoresByLaunchesId(id: String) {
        viewModelScope.launch {
            _liveDataCoresByLaunchesId.value = coresRepository.getAllCoresByLaunchesId(id)
        }
    }
}