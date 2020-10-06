package com.appdevpwl.spacex.data.capsules

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.appdevpwl.spacex.data.Service
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class CapsulesRepository @Inject constructor(private val capsulesDao: CapsulesDao, private val service: Service) {


    fun getAllCapsulesSortTypeDescending() =capsulesDao.getAllCapsulesSortByTypeDescending()

    fun getAllCapsulesSortTypeAscending() =capsulesDao.getAllCapsulesSortByTypeAscending()
    fun getAllCapsulesSortLaunchTimeAscending() =capsulesDao.getAllCapsulesSortByLaunchTimeAscending()
    fun getAllCapsulesSortLaunchTimeDescending() =capsulesDao.getAllCapsulesSortByLaunchTimeDescending()


    val liveData = MutableLiveData<com.appdevpwl.spacex.util.Response<List<Capsule>>>()

    suspend fun getDataFromApiAndSave() {
        val response = service.getCapsules()
        if (response.isSuccessful) {
            val body = response.body()!!
            liveData.value = com.appdevpwl.spacex.util.Response.success(body)
            response.body().let {
                if ((it!=null)&&(!isEmptyDB())) {
                    capsulesDao.replaceAllCapsules(it)
                }
                if ((it!=null)&&(isEmptyDB())) {
                    capsulesDao.insertAllCapsules(it)
                }


            }
        } else {
            
        }


    }


    private fun isEmptyDB(): Boolean {
        return when (capsulesDao.countCapsules()) {
            0 -> true
            else -> false
        }

    }

    fun countCapsulesInDB() {
        capsulesDao.countCapsules()
    }


}
