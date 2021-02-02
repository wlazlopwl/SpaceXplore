package com.appdevpwl.spacex.data.cores

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.appdevpwl.spacex.data.DataStorePreferences
import com.appdevpwl.spacex.data.Service
import com.appdevpwl.spacex.util.Constant.Companion.CORES_LAST_DATE
import com.appdevpwl.spacex.util.Constant.Companion.NO_CONNECTION_MESSAGE
import com.appdevpwl.spacex.util.deviceIsOnline
import com.appdevpwl.spacex.util.getCurrentMillisTime
import javax.inject.Inject

class CoresRepository @Inject constructor(
    private val coresDao: CoresDao,
    private val service: Service,
    private val context: Context,
    private val preferences: DataStorePreferences,
) {
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
                    response.body().let {
                        if (it != null) {
                            saveCoresToDb(it)
                            preferences.saveCurrentUpdateTime(CORES_LAST_DATE,
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

    private suspend fun saveCoresToDb(list: List<CoresItem>) {
        coresDao.replaceAllCores(list)
    }

    fun getDbSize(): Int {
        return coresDao.getSize()
    }

    fun getAllCoresFromDb(): LiveData<List<CoresItem>> {
        return coresDao.getAllCores()

    }
     fun getAllCoresByLaunchesId(id: String): LiveData<List<CoresItem>> {
        return coresDao.getAllCoresByLaunchesId(id)
    }
}