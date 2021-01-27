package com.appdevpwl.spacex.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.appdevpwl.spacex.data.TestEntity
import com.appdevpwl.spacex.data.TestEntityRepository
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val testEntityRepository: TestEntityRepository)   : ViewModel() {


}