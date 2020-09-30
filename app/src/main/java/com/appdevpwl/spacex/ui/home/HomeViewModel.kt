package com.appdevpwl.spacex.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.appdevpwl.spacex.data.TestEntity
import com.appdevpwl.spacex.data.TestEntityRepository
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val testEntityRepository: TestEntityRepository)   : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
         testEntityRepository.insert(TestEntity(1,"test"))
        value= testEntityRepository.allTest()[0].testName
//        value="no test"
    }
    val text: LiveData<String> = _text
}