package com.appdevpwl.spacex.data.cores

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.appdevpwl.spacex.data.Service
import com.appdevpwl.spacex.util.checkIsOnline
import javax.inject.Inject

class CoresRepository @Inject constructor(
    private val coresDao: CoresDao,
    private val service: Service,
    private val context: Context
) {

    val liveData = MutableLiveData<List<CoresItem>>()
    val snackbarText = MutableLiveData<String>()
    val loadingData = MutableLiveData<Boolean>()



    suspend fun getDataFromApiAndSave() {

        when (checkIsOnline(context)) {

            false -> snackbarText.postValue("Check your connection")
            true ->{
                loadingData.postValue(true)
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
                loadingData.postValue(false)
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
        loadingData.postValue(true)

        liveData.value = coresDao.getAllCores()
        loadingData.postValue(false)

    }

    suspend fun getAllCoresByLaunchesId(id: String): List<CoresItem> {
        return coresDao.getAllCoresByLaunchesId(id)
    }
}