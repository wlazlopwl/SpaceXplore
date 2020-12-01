package com.appdevpwl.spacex.ui

import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceFragmentCompat
import com.appdevpwl.spacex.R
import javax.inject.Inject


class SettingsFragment : PreferenceFragmentCompat() {



        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {

            setPreferencesFromResource(R.xml.root_preferences, rootKey)


        }



}