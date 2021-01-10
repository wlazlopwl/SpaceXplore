package com.appdevpwl.spacex.data.cores

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.appdevpwl.spacex.data.Service
import com.appdevpwl.spacex.data.launches.model.Core
import com.appdevpwl.spacex.data.launches.model.LaunchesItem
import com.appdevpwl.spacex.util.Response
import javax.inject.Inject

class CoresRepository @Inject constructor(private val coresDao: CoresDao, private val service: Service) {

    val liveData = MutableLiveData<Response<List<CoresItem>>>()
    val allCoresLiveData = MutableLiveData<List<CoresItem>>()



    suspend fun getDataFromApiAndSave() {
        val response = service.getAllCores()
        if (response.isSuccessful) {
            val body = response.body()!!
            response.body().let {
                if (it != null) {
                    saveCoresToDb(it)
                    Log.d("test","save to db")
                }
                liveData.value = Response.success(body)
            }
        }
        else{
            val error = response.errorBody().toString()
            liveData.value= Response.error(error)

        }
    }

    private suspend fun saveCoresToDb(list: List<CoresItem>) {
        coresDao.replaceAllCores(list)
    }



    suspend fun getAllCoresFromDb() : List<CoresItem>{
//        allCoresLiveData.value=coresDao.getAllCores()
        return coresDao.getAllCores()
    }

    suspend fun getAllCoresByLaunchesId(id:String) : List<CoresItem>?{
//        allCoresLiveData.value=coresDao.getAllCores()
        Log.d("id",id)
        return coresDao.getAllCoresByLaunchesId(id)
    }
}