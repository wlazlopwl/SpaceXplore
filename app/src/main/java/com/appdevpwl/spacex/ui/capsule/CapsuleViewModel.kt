package com.appdevpwl.spacex.ui.capsule

import androidx.lifecycle.*
import com.appdevpwl.spacex.data.DataStorePreferences
import com.appdevpwl.spacex.data.capsules.Capsule
import com.appdevpwl.spacex.data.capsules.CapsulesRepository
import com.appdevpwl.spacex.util.CapsulesSortType
import com.appdevpwl.spacex.util.Constant
import com.appdevpwl.spacex.util.Constant.Companion.CAPSULES_LAST_DATE
import com.appdevpwl.spacex.util.compareMillis
import com.appdevpwl.spacex.util.getCurrentMillisTime
import kotlinx.coroutines.launch
import javax.inject.Inject

class CapsuleViewModel @Inject constructor(
    private val capsulesRepository: CapsulesRepository,
    private val preferences: DataStorePreferences,
) :
    ViewModel() {

    val snackbarText: LiveData<String> = capsulesRepository.snackbarText
    val loadingData: LiveData<Boolean> = capsulesRepository.isLoading

    var capsules = MutableLiveData<List<Capsule>>()
    private val typeAscending = capsulesRepository.getAllCapsulesSortTypeAscending()
    private val typeDescending = capsulesRepository.getAllCapsulesSortTypeDescending()
    val allCapsules: MediatorLiveData<List<Capsule>> = MediatorLiveData()
    private var capsuleSortType = CapsulesSortType.TYPE_ASC

    init {
        getCapsuleData()

        allCapsules.addSource(typeAscending) { result ->
            if (capsuleSortType == CapsulesSortType.TYPE_ASC) {
                result.let { allCapsules.value = it }
            }
        }

        allCapsules.addSource(typeDescending) { result ->
            if (capsuleSortType == CapsulesSortType.TYPE_DESC) {
                result.let { allCapsules.value = it }
            }
        }
    }

    fun sortCapsules(capsuleSortType: CapsulesSortType) {
        when (capsuleSortType) {
            CapsulesSortType.TYPE_ASC -> typeAscending.value?.let { allCapsules.value = it }
            CapsulesSortType.TYPE_DESC -> typeDescending.value?.let { allCapsules.value = it }

        }.also {
            this.capsuleSortType = capsuleSortType
        }
    }

    private fun getCapsuleData() {
        viewModelScope.launch {
            when (capsulesRepository.getDbSize()) {
                0 -> refreshData()
                else -> {
                    val oldTime = preferences.getLastUpdateTime(CAPSULES_LAST_DATE)
                    val timeToFetch: Long? =
                        preferences.getMaxMinutesBeforeFetchAPI(Constant.MAX_TIME_TO_FETCH_MILLIS)
                    val currentTime = getCurrentMillisTime()
                    when (compareMillis(oldTime, currentTime, timeToFetch!!)) {
                        true -> refreshData()
                        else -> capsulesRepository.getAllCapsulesFromDb()
                    }
                }
            }
        }
    }

    fun refreshData() {
        viewModelScope.launch {
            capsulesRepository.fetchDataAndSaveToDb()
        }
    }
}







