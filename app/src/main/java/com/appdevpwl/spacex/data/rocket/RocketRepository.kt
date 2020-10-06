package com.appdevpwl.spacex.data.rocket

import com.appdevpwl.spacex.data.Service

import javax.inject.Inject

class RocketRepository @Inject constructor(private val rocketDao: RocketDao, private val service: Service) {
}