package com.appdevpwl.spacex.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject


class HomeViewModel @Inject constructor() : ViewModel() {

    private val _menuList: MutableLiveData<List<String>> = MutableLiveData()

    val menuList: MutableLiveData<List<String>> = _menuList

    init {
        val menuItem: List<String> =
            listOf("All launches", "Cores", "Capsules", "Rockets", "Company")
        _menuList.value = menuItem
    }
}