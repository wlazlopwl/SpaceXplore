package com.appdevpwl.spacex.ui.launches

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager2.widget.ViewPager2
import com.appdevpwl.spacex.R
import com.appdevpwl.spacex.ui.cores.CoresViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.DaggerFragment
//import kotlinx.android.synthetic.main.fragment_launches.*
import javax.inject.Inject


class LaunchesFragment : DaggerFragment() {

    lateinit var adapter: LaunchesTabsAdapter
    lateinit var viewPager: ViewPager2

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var launchesViewModel: LaunchesViewModel
    private lateinit var coreViewModel: CoresViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        AndroidSupportInjection.inject(this)
        launchesViewModel =
            ViewModelProviders.of(this, viewModelFactory).get(LaunchesViewModel::class.java)
        coreViewModel =
            ViewModelProviders.of(this, viewModelFactory).get(CoresViewModel::class.java)
        return inflater.inflate(R.layout.fragment_launches, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        adapter = LaunchesTabsAdapter(this)
        viewPager = view.findViewById(R.id.launches_vievpager)
        viewPager.adapter = adapter

        val tabLayout = view.findViewById<TabLayout>(R.id.launches_tabs)
//        TabLayoutMediator(tabLayout, launches_vievpager) { tab, position ->
//            when (position) {
//                0 -> tab.text = "Upcoming"
//                else -> tab.text = "Past"
//            }
//
//        }.attach()
    }
}