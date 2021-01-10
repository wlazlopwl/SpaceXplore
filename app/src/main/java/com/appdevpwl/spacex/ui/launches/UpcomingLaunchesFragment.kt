package com.appdevpwl.spacex.ui.launches

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.appdevpwl.spacex.R
import com.appdevpwl.spacex.util.Response
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
        savedInstanceState: Bundle?
    ): View? {
        AndroidSupportInjection.inject(this)
        launchesViewModel =
            ViewModelProviders.of(this, viewModelFactory).get(LaunchesViewModel::class.java)
        return inflater.inflate(R.layout.fragment_upcoming_launches, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        launchesViewModel.getDataFromApi()
        Log.d("test","start get data in fragment")

        launchesViewModel.liveData.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Response.Status.SUCCESS -> {
                    Log.d("test","success")
                    progressBar2.visibility=View.INVISIBLE
                    launchesViewModel.getUpcomingLaunches()
                }
                Response.Status.LOADING -> progressBar2.visibility=View.VISIBLE
            }
        })


        launchesViewModel.upcomingLaunchesLiveData.observe(viewLifecycleOwner, Observer {


            launchesAdapter = LaunchesAdapter()
            rv_upcoming_launches.apply {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(activity)
                adapter = launchesAdapter
                launchesAdapter.addItemsToLaunchesList(it!!)
                Log.d("test","success and download from db last")
            }


        })


    }
}

