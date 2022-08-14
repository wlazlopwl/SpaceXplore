package com.appdevpwl.spacex.data.company

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.appdevpwl.spacex.data.DataStorePreferences
import com.appdevpwl.spacex.data.Service
import com.appdevpwl.spacex.util.Constant
import com.appdevpwl.spacex.util.deviceIsOnline
import com.appdevpwl.spacex.util.getCurrentMillisTime
import javax.inject.Inject

class CompanyRepository @Inject constructor(
    private val companyDao: CompanyDao,
    private val context: Context,
    private val service: Service,
    private val preferences: DataStorePreferences,
) {
    val snackbarText = MutableLiveData<String>()
    val isLoading = MutableLiveData<Boolean>()

    suspend fun fetchDataAndSaveToDb() {
        when (deviceIsOnline(context)) {
            false -> {
                snackbarText.postValue(Constant.NO_CONNECTION_MESSAGE)
                getCompanyFromDb()
            }
            true -> {
                isLoading.postValue(true)
                val response = service.getCompanyInfo()
                if (response.isSuccessful) {
                    response.body().let {
                        if (it != null) {
                            saveCompanyToDb(it)
                            preferences.saveCurrentUpdateTime(Constant.COMPANY_LAST_DATE,
                                getCurrentMillisTime())
                        }
                    }
                } else {
                    snackbarText.postValue(response.errorBody().toString())
                }
            }
        }
        isLoading.postValue(false)
    }

    private suspend fun saveCompanyToDb(company: Company) {
        companyDao.replaceCompany(company)
    }

    fun getDbSize(): Int {
        return companyDao.getSize()
    }

    fun getCompanyFromDb(): LiveData<Company> {
        return companyDao.getCompany()
    }
}