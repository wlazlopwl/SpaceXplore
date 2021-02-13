package com.appdevpwl.spacex.ui.rocket

import androidx.lifecycle.*
import com.appdevpwl.spacex.data.DataStorePreferences
import com.appdevpwl.spacex.data.rocket.RocketRepository
import com.appdevpwl.spacex.data.rocket.model.Rocket
import kotlinx.coroutines.launch
import javax.inject.Inject

class RocketDetailsViewModel @Inject constructor(
    private val rocketRepository: RocketRepository,
    private val preferences: DataStorePreferences,
) : ViewModel() {

    lateinit var rocketLiveData: LiveData<Rocket>

   private  val _massUnit= preferences.massUnit.asLiveData()
    val massUnit: LiveData<String>
    get() = _massUnit

    private  val _lengthUnit= preferences.lengthUnit.asLiveData()
    val lengthUnit: LiveData<String>
        get() = _lengthUnit




    fun getRocketById(id: String) {
        viewModelScope.launch {
            rocketLiveData = rocketRepository.getRocketById(id)
        }
    }



}
