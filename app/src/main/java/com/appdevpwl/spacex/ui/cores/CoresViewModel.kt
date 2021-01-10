package com.appdevpwl.spacex.ui.cores

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appdevpwl.spacex.data.cores.CoresItem
import com.appdevpwl.spacex.data.cores.CoresRepository
import com.appdevpwl.spacex.data.launches.model.LaunchesItem
import com.appdevpwl.spacex.util.Response
import kotlinx.coroutines.launch
import javax.inject.Inject

class CoresViewModel @Inject constructor(private val coresRepository: CoresRepository): ViewModel() {


    val liveData: MutableLiveData<Response<List<CoresItem>>> = coresRepository.liveData
    val _liveDataFromDB= MutableLiveData<List<CoresItem>>()
    val result: LiveData<List<CoresItem>> = _liveDataFromDB

    val _liveDataCoresByLaunchesId= MutableLiveData<List<CoresItem>>()
    val liveDataCoresByLaunchesId: LiveData<List<CoresItem>> = _liveDataCoresByLaunchesId
    fun getDataFromApi(){
        viewModelScope.launch {
            coresRepository.getDataFromApiAndSave()
        }
    }

    fun getAllCores(){
        viewModelScope.launch {
            _liveDataFromDB.value=coresRepository.getAllCoresFromDb()
        }
    }

    fun getAllCoresByLaunchesId(id: String){
        viewModelScope.launch {
           _liveDataCoresByLaunchesId.value=coresRepository.getAllCoresByLaunchesId(id)
        }
    }
}