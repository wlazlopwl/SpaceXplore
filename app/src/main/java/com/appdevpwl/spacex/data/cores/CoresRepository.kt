package com.appdevpwl.spacex.data.cores

import com.appdevpwl.spacex.data.Service
import javax.inject.Inject

class CoresRepository @Inject constructor(private val coresDao: CoresDao, private val service: Service) {
}