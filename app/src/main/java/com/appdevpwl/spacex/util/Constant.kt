package com.appdevpwl.spacex.util

import androidx.datastore.preferences.core.stringPreferencesKey

class Constant {

    companion object {
        const val BASE_URL = "https://api.spacexdata.com/"

        //preferences key
        const val CORES_LAST_DATE: String = "1579710258000"


        //TIME TO GET DATA FROM API
        const val MAX_TIME_TO_FETCH_MILLIS : String = "MAX_TIME"


    }
}