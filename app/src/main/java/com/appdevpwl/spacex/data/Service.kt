package com.appdevpwl.spacex.data

import com.appdevpwl.spacex.data.capsules.Capsule
import com.appdevpwl.spacex.data.rocket.Rocket
import retrofit2.Response
import retrofit2.http.GET

interface Service {

    @GET("v3/capsules")
   suspend fun getCapsules() : Response<List<Capsule>>

    @GET("v3/rockets")
    suspend fun getRockets() : Response<List<Rocket>>
}