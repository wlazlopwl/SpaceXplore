package com.appdevpwl.spacex.util


class Constant {

    companion object {

        const val BASE_URL = "https://api.spacexdata.com/"

        const val NO_CONNECTION_MESSAGE = "Check Internet connection"

        //preferences key
        const val CORES_LAST_DATE: String = "cores_last"
        const val ROCKET_LAST_DATE: String = "rocket_last"
        const val LAUNCHES_LAST_DATE: String = "launches_last"
        const val CAPSULES_LAST_DATE: String = "capsules_last"
        const val COMPANY_LAST_DATE: String="company_last"
        const val MASS_UNIT: String="MASS_UNIT"
        const val LENGTH_UNIT: String="LENGTH_UNIT"

        //TIME TO GET DATA FROM API
        const val MAX_TIME_TO_FETCH_MILLIS: String = "MAX_TIME"


    }
}