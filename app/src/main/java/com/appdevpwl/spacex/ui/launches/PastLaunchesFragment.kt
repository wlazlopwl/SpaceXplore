package com.appdevpwl.spacex.ui.launches

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.appdevpwl.spacex.R
import com.appdevpwl.spacex.data.launches.model.LaunchesItem
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_past_lunches.*
import javax.inject.Inject


class PastLaunchesFragment : DaggerFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var launchesViewModel: LaunchesViewModel
    private lateinit var launchesAdapter: LaunchesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        AndroidSupportInjection.inject(this)
        launchesViewModel =
            ViewModelProviders.of(this, viewModelFactory).get(LaunchesViewModel::class.java)

        return inflater.inflate(R.layout.fragment_past_lunches, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        launchesViewModel.pastLaunchesLiveData.observe(viewLifecycleOwner, Observer {
            initRecyclerView(it)
        })
    }

    private fun initRecyclerView(data: List<LaunchesItem>) {
        launchesAdapter = LaunchesAdapter()
        rv_past_launches.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(activity)
            adapter = launchesAdapter
            launchesAdapter.addItemsToLaunchesList(data)
        }
    }


}