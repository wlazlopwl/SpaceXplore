package com.appdevpwl.spacex.data.launches

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.appdevpwl.spacex.data.Service
import com.appdevpwl.spacex.data.launches.model.LaunchesItem
import com.appdevpwl.spacex.util.Response

import javax.inject.Inject

class LaunchesRepository @Inject constructor(private val launchesDao: LaunchesDao, private val service: Service) {

    val liveData = MutableLiveData<Response<List<LaunchesItem>>>()
    val allLaunchesLiveData = MutableLiveData<List<LaunchesItem>>()
    val upcomingLaunchesLiveData = MutableLiveData<List<LaunchesItem>>()
    val pastLaunchesLiveData = MutableLiveData<List<LaunchesItem>>()

    suspend fun getDataFromApiAndSave() {
        val response = service.getAllLaunches()
        if (response.isSuccessful) {
            val body = response.body()!!
            response.body().let {
                if (it != null) {
                    savelaunchesToDb(it)
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

    private suspend fun savelaunchesToDb(list: List<LaunchesItem>) {
        launchesDao.replaceAllLaunches(list)
    }

//    suspend fun getRocketById(id: Int){
//        rocketLiveData.value = launchesDao.getLaunchesByLaunchesId(id)
//    }

    suspend fun getAllLaunchesFromDb() {
        allLaunchesLiveData.value=launchesDao.getAllLaunches()
    }
     fun getUpcomingLaunchesFromDb() {
        upcomingLaunchesLiveData.value=launchesDao.getUpcomingLaunches()
         Log.d("test","start get data from db")
    }
    fun getPastLaunchesFromDb() {
        pastLaunchesLiveData.value=launchesDao.getPastLaunches()
    }


}