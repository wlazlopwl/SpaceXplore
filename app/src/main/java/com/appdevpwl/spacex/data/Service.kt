package com.appdevpwl.spacex.data

import com.appdevpwl.spacex.data.capsules.Capsule
import com.appdevpwl.spacex.data.company.Company
import com.appdevpwl.spacex.data.cores.CoresItem
import com.appdevpwl.spacex.data.launches.model.LaunchesItem
import com.appdevpwl.spacex.data.rocket.model.Rocket
import retrofit2.Response
import retrofit2.http.GET

interface Service {

    @GET("v4/capsules")
    suspend fun getCapsules(): Response<List<Capsule>>

    @GET("v4/rockets")
    suspend fun getRockets(): Response<List<Rocket>>

    @GET("v4/launches")
    suspend fun getAllLaunches(): Response<List<LaunchesItem>>

    @GET("v4/cores")
    suspend fun getAllCores(): Response<List<CoresItem>>

    @GET("v4/company")
    suspend fun getCompanyInfo(): Response<Company>
}