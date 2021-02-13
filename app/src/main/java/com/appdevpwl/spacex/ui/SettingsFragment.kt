package com.appdevpwl.spacex.ui

import android.content.Context
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.preference.ListPreference
import androidx.preference.PreferenceFragmentCompat
import com.appdevpwl.spacex.R
import com.appdevpwl.spacex.data.DataStorePreferences
import com.appdevpwl.spacex.util.Constant
import com.appdevpwl.spacex.util.Constant.Companion.LENGTH_UNIT
import com.appdevpwl.spacex.util.Constant.Companion.MASS_UNIT
import com.appdevpwl.spacex.util.SnackbarType
import dagger.android.support.AndroidSupportInjection
import kotlinx.coroutines.launch
import javax.inject.Inject


class SettingsFragment : PreferenceFragmentCompat() {


    @Inject
    lateinit var preferences: DataStorePreferences

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {


        setPreferencesFromResource(R.xml.root_preferences, rootKey)

        var maxTime: ListPreference? = findPreference("MAX_TIME")
        var massUnit: ListPreference? = findPreference("MASS_UNIT")
        var lengthUnit: ListPreference? = findPreference("LENGTH_UNIT")

        massUnit!!.setOnPreferenceChangeListener { _, newValue ->
            viewLifecycleOwner.lifecycleScope.launch {
                preferences.saveMassUnit(MASS_UNIT, newValue.toString())
                SnackbarType.enableSnackbar(requireView(), "Mass unit changed to $newValue")
            }
            true
        }

        lengthUnit!!.setOnPreferenceChangeListener { _, newValue ->
            viewLifecycleOwner.lifecycleScope.launch {
                preferences.saveLengthUnit(LENGTH_UNIT, newValue.toString())
                SnackbarType.enableSnackbar(requireView(), "Length unit changed to $newValue")
            }
            true
        }


        maxTime!!.setOnPreferenceChangeListener { pref, newValue ->
            viewLifecycleOwner.lifecycleScope.launch {
                preferences.saveMaxMinutesBeforeFetchAPI(Constant.MAX_TIME_TO_FETCH_MILLIS,
                    newValue.toString().toLong())
                SnackbarType.enableSnackbar(requireView(), "Changed fetch data time")
            }
            true
        }

    }


}