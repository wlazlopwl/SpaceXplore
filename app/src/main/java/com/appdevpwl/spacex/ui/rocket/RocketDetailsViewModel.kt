package com.appdevpwl.spacex.ui.rocket

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appdevpwl.spacex.data.rocket.RocketRepository
import com.appdevpwl.spacex.data.rocket.model.Rocket
import kotlinx.coroutines.launch
import javax.inject.Inject

class RocketDetailsViewModel @Inject constructor(private val rocketRepository: RocketRepository)  : ViewModel() {

    val rocketLiveData : MutableLiveData<Rocket> = rocketRepository.rocketLiveData

    fun getRocketById(id:Int){
        viewModelScope.launch {
            rocketRepository.getRocketById(id)
        }
    }

}