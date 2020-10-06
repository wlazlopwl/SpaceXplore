package com.appdevpwl.spacex.ui.rocket

import androidx.lifecycle.ViewModel
import com.appdevpwl.spacex.data.rocket.RocketRepository
import javax.inject.Inject

class RocketViewModel @Inject constructor(private val rocketRepository: RocketRepository) : ViewModel() {
}