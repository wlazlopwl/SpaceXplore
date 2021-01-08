package com.appdevpwl.spacex.ui.launches

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appdevpwl.spacex.data.launches.LaunchesRepository
import com.appdevpwl.spacex.data.launches.model.LaunchesItem
import com.appdevpwl.spacex.util.Response
import kotlinx.coroutines.launch
import javax.inject.Inject

class LaunchesViewModel @Inject constructor(private val launchesRepository: LaunchesRepository) : ViewModel() {


    val liveData: MutableLiveData<Response<List<LaunchesItem>>> = launchesRepository.liveData

    val liveDataDb : MutableLiveData<List<LaunchesItem>> = launchesRepository.allLaunchesLiveData
    val upcomingLaunchesLiveData : MutableLiveData<List<LaunchesItem>> = launchesRepository.upcomingLaunchesLiveData
    val pastLaunchesLiveData : MutableLiveData<List<LaunchesItem>> = launchesRepository.pastLaunchesLiveData


    fun getAllLaunches(){
        viewModelScope.launch {
            launchesRepository.getAllLaunchesFromDb()
        }
    }
    fun getUpcomingLaunches(){
        viewModelScope.launch {
            Log.d("test","getupcoming fun")
            launchesRepository.getUpcomingLaunchesFromDb()
        }
    }
    fun getPastLaunches(){
        viewModelScope.launch {
            launchesRepository.getPastLaunchesFromDb()
        }
    }

    fun getDataFromApi(){
        viewModelScope.launch {
            launchesRepository.getDataFromApiAndSave()
        }
    }

}