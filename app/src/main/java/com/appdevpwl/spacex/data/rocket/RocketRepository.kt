package com.appdevpwl.spacex.data.rocket

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.appdevpwl.spacex.data.Service
import com.appdevpwl.spacex.data.capsules.Capsule
import com.appdevpwl.spacex.data.rocket.model.Rocket
import com.appdevpwl.spacex.util.Response

import javax.inject.Inject

class RocketRepository @Inject constructor(private val rocketDao: RocketDao, private val service: Service) {

    val liveData = MutableLiveData<Response<List<Rocket>>>()
    val rocketLiveData = MutableLiveData<Rocket>()

    suspend fun getDataFromApiAndSave() {
        val response = service.getRockets()
        if (response.isSuccessful) {
            val body = response.body()!!
            liveData.value = com.appdevpwl.spacex.util.Response.success(body)
            response.body().let {
                if (it != null) {
                    saveRocketsToDb(it)
                }
            }
        }
        else{
            val error = response.errorBody().toString()
            liveData.value= Response.error(error)

        }
    }

    private suspend fun saveRocketsToDb(list: List<Rocket>) {
        rocketDao.replaceAllRockets(list)
    }

    suspend fun getRocketById(id: Int){
        rocketLiveData.value = rocketDao.getRocketByRocketId(id)
    }

//    suspend fun getRocketsFromDb(): LiveData<List<Rocket>> {
//        return rocketDao.getAllRockets()
//    }


}