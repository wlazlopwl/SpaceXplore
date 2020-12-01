package com.appdevpwl.spacex.ui.rocket

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appdevpwl.spacex.data.rocket.model.Rocket
import com.appdevpwl.spacex.data.rocket.RocketRepository
import com.appdevpwl.spacex.util.Response
import kotlinx.coroutines.launch
import javax.inject.Inject

class RocketViewModel @Inject constructor(private val rocketRepository: RocketRepository) : ViewModel() {

    val liveData: MutableLiveData<Response<List<Rocket>>> = rocketRepository.liveData

    fun getDataFromApi(){
        viewModelScope.launch {
            rocketRepository.getDataFromApiAndSave()
        }
    }

//    fun getDataFromDb(){
//        viewModelScope.launch {
//            rocketRepository.getRocketsFromDb()
//        }
//    }

}
