package com.appdevpwl.spacex.data.cores

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.appdevpwl.spacex.data.DataStorePreferences
import com.appdevpwl.spacex.data.Service
import com.appdevpwl.spacex.util.Constant
import com.appdevpwl.spacex.util.Constant.Companion.CORES_LAST_DATE
import com.appdevpwl.spacex.util.Constant.Companion.NO_CONNECTION_MESSAGE
import com.appdevpwl.spacex.util.deviceIsOnline
import com.appdevpwl.spacex.util.getCurrentMillisTime
import com.appdevpwl.spacex.util.millisToDate
import javax.inject.Inject

class CoresRepository @Inject constructor(
    private val coresDao: CoresDao,
    private val service: Service,
    private val context: Context,
    private val preferences: DataStorePreferences
) {

    val coresLiveData = MutableLiveData<List<CoresItem>>()
    val snackbarText = MutableLiveData<String>()
    val isLoading = MutableLiveData<Boolean>()


    suspend fun fetchDataAndSaveToDb() {

        when (deviceIsOnline(context)) {

            false -> {
                snackbarText.postValue(NO_CONNECTION_MESSAGE)
                getAllCoresFromDb()
            }
            true -> {
                isLoading.postValue(true)
                val response = service.getAllCores()
                if (response.isSuccessful) {
                    val body = response.body()
                    response.body().let {
                        if (it != null) {
                            saveCoresToDb(it)
                            preferences.saveCurrentUpdateTime(CORES_LAST_DATE,
                                getCurrentMillisTime())
                        }
                        coresLiveData.value = body!!
                    }
                } else {
                    snackbarText.postValue(response.errorBody().toString())
                }
                isLoading.postValue(false)
            }
        }
    }

    private suspend fun saveCoresToDb(list: List<CoresItem>) {
        coresDao.replaceAllCores(list)

    }

    fun getDbSize(): Int {
        return coresDao.getSize()
    }

    suspend fun getAllCoresFromDb() {
        isLoading.postValue(true)

        coresLiveData.value = coresDao.getAllCores()
        isLoading.postValue(false)
        snackbarText.postValue(millisToDate(preferences.getLastUpdateTime(CORES_LAST_DATE)).toString())

    }

    suspend fun getAllCoresByLaunchesId(id: String): List<CoresItem> {
        return coresDao.getAllCoresByLaunchesId(id)
    }
}