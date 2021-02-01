package com.appdevpwl.spacex.data.capsules

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.appdevpwl.spacex.data.DataStorePreferences
import com.appdevpwl.spacex.data.Service
import com.appdevpwl.spacex.util.Constant.Companion.CAPSULES_LAST_DATE
import com.appdevpwl.spacex.util.Constant.Companion.NO_CONNECTION_MESSAGE
import com.appdevpwl.spacex.util.deviceIsOnline
import com.appdevpwl.spacex.util.getCurrentMillisTime
import com.appdevpwl.spacex.util.millisToDate
import javax.inject.Inject

class CapsulesRepository @Inject constructor(
    private val capsulesDao: CapsulesDao,
    private val service: Service,
    private val context: Context,
    private val preferences: DataStorePreferences,
) {

    val snackbarText = MutableLiveData<String>()
    val isLoading = MutableLiveData<Boolean>()
    val capsuleLiveData = MutableLiveData<List<Capsule>>()

    fun getAllCapsulesSortTypeDescending() = capsulesDao.getAllCapsulesSortByTypeDescending()

    fun getAllCapsulesSortTypeAscending() = capsulesDao.getAllCapsulesSortByTypeAscending()
    fun getAllCapsulesSortLaunchTimeAscending() =
        capsulesDao.getAllCapsulesSortByLaunchTimeAscending()

    fun getAllCapsulesSortLaunchTimeDescending() =
        capsulesDao.getAllCapsulesSortByLaunchTimeDescending()


    suspend fun fetchDataAndSaveToDb() {

        when (deviceIsOnline(context)) {

            false -> {
                snackbarText.postValue(NO_CONNECTION_MESSAGE)
                getAllRocketsFromDb()
            }
            true -> {
                isLoading.postValue(true)
                val response = service.getCapsules()
                if (response.isSuccessful) {
                    val body = response.body()
                    response.body().let {
                        if (it != null) {
                            saveRocketsToDb(it)
                            preferences.saveCurrentUpdateTime(CAPSULES_LAST_DATE,
                                getCurrentMillisTime())
                        }
                        capsuleLiveData.value = body!!
                    }
                } else {
                    snackbarText.postValue(response.errorBody().toString())
                }
                isLoading.postValue(false)
            }
        }
    }

    private suspend fun saveRocketsToDb(list: List<Capsule>) {
        capsulesDao.replaceAllCapsules(list)
    }

    suspend fun getAllRocketsFromDb() {
        isLoading.postValue(true)

        capsuleLiveData.value = capsulesDao.getAllCapsules()
        isLoading.postValue(false)
        snackbarText.postValue(millisToDate(preferences.getLastUpdateTime(CAPSULES_LAST_DATE)).toString())
    }


    fun getDbSize(): Int {
        return capsulesDao.getSize()
    }


}
