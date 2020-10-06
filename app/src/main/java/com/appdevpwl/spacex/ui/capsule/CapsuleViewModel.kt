package com.appdevpwl.spacex.ui.capsule

import android.util.Log
import androidx.lifecycle.*
import com.appdevpwl.spacex.data.capsules.Capsule
import com.appdevpwl.spacex.data.capsules.CapsulesRepository
import com.appdevpwl.spacex.util.CapsulesSortType
import com.appdevpwl.spacex.util.Response
import kotlinx.coroutines.launch
import javax.inject.Inject

class CapsuleViewModel @Inject constructor(private val capsulesRepository: CapsulesRepository) : ViewModel() {

    val liveData: LiveData<Response<List<Capsule>>> = capsulesRepository.liveData
    var capsules = MutableLiveData<List<Capsule>>()
    private val typeAscending = capsulesRepository.getAllCapsulesSortTypeAscending()
    private val typeDescending = capsulesRepository.getAllCapsulesSortTypeDescending()
    private val launchTimeDescending = capsulesRepository.getAllCapsulesSortLaunchTimeDescending()
    private val launchTimeAscending = capsulesRepository.getAllCapsulesSortLaunchTimeAscending()
    val allCapsules:MediatorLiveData<List<Capsule>> = MediatorLiveData()
    private var capsuleSortType = CapsulesSortType.TYPE_ASC

    init {
        allCapsules.addSource(typeAscending){ result ->
            if (capsuleSortType == CapsulesSortType.TYPE_ASC){
                Log.d("init","dziala")
                result.let { allCapsules.value=it }
            }
        }

        allCapsules.addSource(typeDescending){ result ->
            if (capsuleSortType == CapsulesSortType.TYPE_DESC){
                result.let { allCapsules.value=it }
            }
        }
        allCapsules.addSource(launchTimeDescending){result ->
            if (launchTimeDescending==CapsulesSortType.TIME_DESC){
                result.let { allCapsules.value=it }
            }
        }
        allCapsules.addSource(launchTimeAscending){result ->
            if (launchTimeAscending==CapsulesSortType.TIME_ASC){
                result.let { allCapsules.value=it }
            }
        }
    }

    fun sortCapsules(capsuleSortType: CapsulesSortType){
        when(capsuleSortType){

            CapsulesSortType.TYPE_ASC -> typeAscending.value?.let { allCapsules.value=it }
            CapsulesSortType.TYPE_DESC -> typeDescending.value?.let { allCapsules.value=it }
            CapsulesSortType.TIME_ASC -> launchTimeAscending.value?.let { allCapsules.value=it }
            CapsulesSortType.TIME_DESC -> launchTimeDescending.value?.let { allCapsules.value=it }
            else -> capsulesRepository.countCapsulesInDB()
        }.also {
            this.capsuleSortType = capsuleSortType

        }
            }
    fun getDataFromApiAndSave() {
        viewModelScope.launch {
            capsulesRepository.getDataFromApiAndSave()
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







