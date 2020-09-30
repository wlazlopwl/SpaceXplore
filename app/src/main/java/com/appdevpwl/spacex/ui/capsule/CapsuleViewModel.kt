package com.appdevpwl.spacex.ui.capsule

import androidx.lifecycle.*
import com.appdevpwl.spacex.data.capsules.Capsule
import com.appdevpwl.spacex.data.capsules.CapsulesRepository
import com.appdevpwl.spacex.util.Response
import kotlinx.coroutines.launch
import javax.inject.Inject

class CapsuleViewModel @Inject constructor(private val capsulesRepository: CapsulesRepository) : ViewModel() {

    val liveData: LiveData<Response<List<Capsule>>> = capsulesRepository.liveData
    var capsules = MutableLiveData<List<Capsule>>()

    fun getData() {
        viewModelScope.launch {
            capsulesRepository.getData()
        }
    }




//    fun chooseData() {
//        when (capsulesRepository.isEmptyDB()) {
//            true -> viewModelScope.launch { capsulesRepository.getAllCapsulesFromApi() }
//            false -> viewModelScope.launch { capsules.postValue(capsulesRepository.getAllCapsules().value) }
//        }
//    }

    fun countCapsulesInDB() {
        capsulesRepository.countCapsulesInDB()
    }


}







