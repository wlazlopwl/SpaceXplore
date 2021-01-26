package com.appdevpwl.spacex.util

import androidx.datastore.preferences.core.stringPreferencesKey

class Constant {

    companion object {
        const val BASE_URL = "https://api.spacexdata.com/"

        const val NO_CONNECTION_MESSAGE="Check Internet connection"

        //preferences key
        const val CORES_LAST_DATE: String = ""
        const val ROCKET_LAST_DATE: String = ""


        //TIME TO GET DATA FROM API
        const val MAX_TIME_TO_FETCH_MILLIS : String = "MAX_TIME"


    }
}