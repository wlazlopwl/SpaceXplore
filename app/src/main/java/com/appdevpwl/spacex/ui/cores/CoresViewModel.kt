package com.appdevpwl.spacex.ui.cores

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appdevpwl.spacex.data.cores.CoresItem
import com.appdevpwl.spacex.data.cores.CoresRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class CoresViewModel @Inject constructor(private val coresRepository: CoresRepository) :
    ViewModel() {


    val liveData: MutableLiveData<List<CoresItem>> = coresRepository.liveData
    private val _liveDataCoresByLaunchesId = MutableLiveData<List<CoresItem>>()
    val liveDataCoresByLaunchesId: LiveData<List<CoresItem>> = _liveDataCoresByLaunchesId
    val snackbarText: LiveData<String> = coresRepository.snackbarText
    val loadingData: LiveData<Boolean> = coresRepository.loadingData


    init {
        getDataFromApi()
    }

    fun getDataFromApi() {
        viewModelScope.launch {
            when (coresRepository.getDbSize()) {
                0 -> refreshData()
                else ->  {

                    coresRepository.getAllCoresFromDb()
                }
            }

        }
    }

    suspend fun refreshData(){
        coresRepository.getDataFromApiAndSave()
    }

    fun getAllCoresByLaunchesId(id: String) {
        viewModelScope.launch {
            _liveDataCoresByLaunchesId.value = coresRepository.getAllCoresByLaunchesId(id)
        }
    }
}