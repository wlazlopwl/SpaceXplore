package com.appdevpwl.spacex.ui

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.appdevpwl.spacex.R


class SettingsFragment : PreferenceFragmentCompat() {


    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {

        setPreferencesFromResource(R.xml.root_preferences, rootKey)


    }


}