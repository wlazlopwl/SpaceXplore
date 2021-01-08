package com.appdevpwl.spacex.ui.cores

import androidx.lifecycle.ViewModel
import com.appdevpwl.spacex.data.cores.CoresRepository
import javax.inject.Inject

class CoresViewModel @Inject constructor(private val coresRepository: CoresRepository): ViewModel() {
}