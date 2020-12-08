package com.appdevpwl.spacex.ui.launches

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appdevpwl.spacex.data.launches.LaunchesRepository
import com.appdevpwl.spacex.data.launches.model.Launches
import com.appdevpwl.spacex.data.launches.model.LaunchesItem
import com.appdevpwl.spacex.data.rocket.RocketRepository
import com.appdevpwl.spacex.data.rocket.model.Rocket
import com.appdevpwl.spacex.util.Response
import kotlinx.coroutines.launch
import javax.inject.Inject

class LaunchesViewModel @Inject constructor(private val launchesRepository: LaunchesRepository) : ViewModel() {


    val liveData: MutableLiveData<Response<List<LaunchesItem>>> = launchesRepository.liveData

    val liveDataDb : MutableLiveData<List<LaunchesItem>> = launchesRepository.allLaunchesLiveData

    fun getAllLaunches(){
        viewModelScope.launch {
            launchesRepository.getAllLaunchesFromDb()
        }
    }

    fun getDataFromApi(){
        viewModelScope.launch {
            launchesRepository.getDataFromApiAndSave()
        }
    }
}