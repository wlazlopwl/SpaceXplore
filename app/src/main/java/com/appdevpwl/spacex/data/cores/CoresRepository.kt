package com.appdevpwl.spacex.data.cores

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.appdevpwl.spacex.data.Service
import com.appdevpwl.spacex.util.checkIsOnline
import javax.inject.Inject

class CoresRepository @Inject constructor(
    private val coresDao: CoresDao,
    private val service: Service
) {

    val liveData = MutableLiveData<List<CoresItem>>()
    val snackbarText = MutableLiveData<String>()


    suspend fun getDataFromApiAndSave() {
        checkIsOnline()
        snackbarText.postValue("działa")
        val response = service.getAllCores()
        if (response.isSuccessful) {
            val body = response.body()!!
            response.body().let {
                if (it != null) {
                    saveCoresToDb(it)
                }
                liveData.value = body
            }
        } else {
            snackbarText.postValue(response.errorBody().toString())


        }
    }

    private suspend fun saveCoresToDb(list: List<CoresItem>) {
        coresDao.replaceAllCores(list)
    }

    fun getDbSize(): Int {
        return coresDao.getSize()
    }

    suspend fun getAllCoresFromDb() {
        snackbarText.postValue("Data from database")
        liveData.value = coresDao.getAllCores()
    }

    suspend fun getAllCoresByLaunchesId(id: String): List<CoresItem> {
        return coresDao.getAllCoresByLaunchesId(id)
    }
}