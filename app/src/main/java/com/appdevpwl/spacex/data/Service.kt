package com.appdevpwl.spacex.data

import androidx.lifecycle.MutableLiveData
import com.appdevpwl.spacex.data.capsules.Capsule
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface Service {

    @GET("v3/capsules")
   suspend fun getCapsules() : Response<List<Capsule>>
}