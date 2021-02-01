package com.appdevpwl.spacex.ui.home

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.appdevpwl.spacex.data.HomeRepository
import javax.inject.Inject


class HomeViewModel @Inject constructor(private val homeRepository: HomeRepository)   : ViewModel() {

    private val _menuList: MutableLiveData<List<String>> = MutableLiveData()

    val menuList: MutableLiveData<List<String>> = _menuList

    init {
        var menuItem: List<String> = listOf("All launches", "Cores", "Capsules", "Rockets")
        _menuList.value=menuItem
    }
}