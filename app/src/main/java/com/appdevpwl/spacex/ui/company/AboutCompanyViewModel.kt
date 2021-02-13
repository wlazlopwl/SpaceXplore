package com.appdevpwl.spacex.ui.company

import android.content.Intent
import android.net.Uri
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appdevpwl.spacex.data.DataStorePreferences
import com.appdevpwl.spacex.data.company.Company
import com.appdevpwl.spacex.data.company.CompanyRepository
import com.appdevpwl.spacex.util.Constant
import com.appdevpwl.spacex.util.compareMillis
import com.appdevpwl.spacex.util.getCurrentMillisTime
import kotlinx.coroutines.launch
import javax.inject.Inject


class AboutCompanyViewModel @Inject constructor(
    private val companyRepository: CompanyRepository,
    private val preferences: DataStorePreferences,
) :
    ViewModel() {


    val companyLiveData: LiveData<Company> = companyRepository.getCompanyFromDb()
    val snackbarText: LiveData<String> = companyRepository.snackbarText
    val loadingData: LiveData<Boolean> = companyRepository.isLoading


    init {
        getData()

    }


    private fun getData() {
        viewModelScope.launch {
            when (companyRepository.getDbSize()) {
                0 -> refreshData()
                else -> {
                    val oldTime = preferences.getLastUpdateTime(Constant.COMPANY_LAST_DATE)
                    val timeToFetch: Long? =
                        preferences.getMaxMinutesBeforeFetchAPI(Constant.MAX_TIME_TO_FETCH_MILLIS)
                    val currentTime = getCurrentMillisTime()
                    when (compareMillis(oldTime, currentTime, timeToFetch!!)) {
                        true -> refreshData()
                        else -> companyRepository.getCompanyFromDb()
                    }
                }
            }
        }
    }


    fun refreshData() {
        viewModelScope.launch {
            companyRepository.fetchDataAndSaveToDb()
        }

    }

    fun getCompanyAddress(): String? {
        return companyLiveData.value?.headquarters?.let {headquarters ->
            headquarters.address+"+"+headquarters.city+"+"+headquarters.state
        }
    }


}