package com.appdevpwl.spacex.ui.launches

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.appdevpwl.spacex.R
import com.appdevpwl.spacex.data.launches.model.LaunchesItem
import com.appdevpwl.spacex.databinding.FragmentUpcomingLaunchesBinding
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_upcoming_launches.*
import javax.inject.Inject


class UpcomingLaunchesFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var launchesViewModel: LaunchesViewModel
    private lateinit var launchesAdapter: LaunchesAdapter

    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        AndroidSupportInjection.inject(this)
        launchesViewModel =
            ViewModelProviders.of(this, viewModelFactory).get(LaunchesViewModel::class.java)
        val binding: FragmentUpcomingLaunchesBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_upcoming_launches, container, false)
        binding.viewModel=launchesViewModel
        binding.lifecycleOwner=viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        launchesViewModel.upcomingLaunchesLiveData.observe(viewLifecycleOwner, Observer {
            initRecyclerView(it)
        })


    }

    private fun initRecyclerView(data: List<LaunchesItem>?) {
        launchesAdapter = LaunchesAdapter()
        rv_upcoming_launches.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(activity)
            adapter = launchesAdapter
            launchesAdapter.addItemsToLaunchesList(data!!)

        }
    }
}

