package com.appdevpwl.spacex.data.capsules

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.appdevpwl.spacex.data.DataStorePreferences
import com.appdevpwl.spacex.data.Service
import com.appdevpwl.spacex.util.Constant.Companion.CAPSULES_LAST_DATE
import com.appdevpwl.spacex.util.Constant.Companion.NO_CONNECTION_MESSAGE
import com.appdevpwl.spacex.util.deviceIsOnline
import com.appdevpwl.spacex.util.getCurrentMillisTime
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
                getAllCapsulesFromDb()
            }
            true -> {
                isLoading.postValue(true)
                val response = service.getCapsules()
                if (response.isSuccessful) {
                    val body = response.body()
                    response.body().let {
                        if (it != null) {
                            saveCapsulesToDb(it)
                            preferences.saveCurrentUpdateTime(CAPSULES_LAST_DATE,
                                getCurrentMillisTime())
                        }
                    }
                } else {
                    snackbarText.postValue(response.errorBody().toString())
                }

            }
        }
        isLoading.postValue(false)
    }

    private suspend fun saveCapsulesToDb(list: List<Capsule>) {
        capsulesDao.replaceAllCapsules(list)
    }

    fun getAllCapsulesFromDb(): LiveData<List<Capsule>> {
        return capsulesDao.getAllCapsules()
//        snackbarText.postValue(millisToDate(preferences.getLastUpdateTime(CAPSULES_LAST_DATE)).toString())
    }


    fun getDbSize(): Int {
        return capsulesDao.getSize()
    }


}
