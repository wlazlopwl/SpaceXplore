package com.appdevpwl.spacex.ui.rocket

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appdevpwl.spacex.R
import com.appdevpwl.spacex.data.DataStorePreferences
import com.appdevpwl.spacex.data.rocket.RocketRepository
import com.appdevpwl.spacex.data.rocket.model.Rocket
import com.appdevpwl.spacex.util.Constant.Companion.MAX_TIME_TO_FETCH_MILLIS
import com.appdevpwl.spacex.util.Constant.Companion.ROCKET_LAST_DATE
import com.appdevpwl.spacex.util.compareMillis
import com.appdevpwl.spacex.util.getCurrentMillisTime
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import kotlinx.coroutines.launch
import javax.inject.Inject

class RocketViewModel @Inject constructor(
    private val rocketRepository: RocketRepository,
    private val preferences: DataStorePreferences
) : ViewModel() {

    val rocketLiveData: MutableLiveData<List<Rocket>> = rocketRepository.rocketLiveData
    val snackbarText: LiveData<String> = rocketRepository.snackbarText
    val loadingData: LiveData<Boolean> = rocketRepository.isLoading


    init {
        getRocketData()
    }

    fun getRocketData() {
        viewModelScope.launch {
            when (rocketRepository.getDbSize()) {
                0 -> refreshData()
                else -> {
                    val oldTime = preferences.getLastUpdateTime(ROCKET_LAST_DATE)
                    val timeToFetch: Long? =
                        preferences.getMaxMinutesBeforeFetchAPI(MAX_TIME_TO_FETCH_MILLIS)
                    val currentTime = getCurrentMillisTime()
                    when (compareMillis(oldTime!!, currentTime, timeToFetch!!)) {
                        true -> refreshData()
                        else -> rocketRepository.getAllRocketsFromDb()
                    }
                }


            }
        }
    }

    fun refreshData() {
        viewModelScope.launch {
            rocketRepository.fetchDataAndSaveToDb()

        }

    }

    companion object{
        @JvmStatic
        @BindingAdapter("imageUrl")
        fun loadImage(imgView: ImageView, url:String?){

            Glide.with(imgView.context).load(url)
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean,
                    ): Boolean {

                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean,
                    ): Boolean {

                        return false
                    }
                })
                .error(R.drawable.ic_error_black_24dp)
                .fallback(R.drawable.ic_error_black_24dp)
                .centerCrop()
                .into(imgView)

        }
    }


}
