package com.appdevpwl.spacex.ui.launches

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class LaunchesTabsAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return if (position == 0) UpcomingLaunchesFragment() else PastLaunchesFragment()
    }
}