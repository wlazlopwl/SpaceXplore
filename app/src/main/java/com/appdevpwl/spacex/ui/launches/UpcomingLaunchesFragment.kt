package com.appdevpwl.spacex.ui.launches

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.appdevpwl.spacex.R
import com.appdevpwl.spacex.ui.rocket.RocketAdapter
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_rocket.*
import kotlinx.android.synthetic.main.fragment_upcoming_launches.*
import javax.inject.Inject


class UpcomingLaunchesFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var launchesViewModel: LaunchesViewModel
    private lateinit var launchesAdapter: LaunchesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        AndroidSupportInjection.inject(this)
        launchesViewModel= ViewModelProviders.of(this, viewModelFactory).get(LaunchesViewModel::class.java)
        return inflater.inflate(R.layout.fragment_upcoming_launches, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d("2", "2")


    launchesViewModel.getDataFromApi()

        launchesViewModel.liveData.observe(viewLifecycleOwner, Observer {


            launchesAdapter = LaunchesAdapter()
            rv_upcoming_launches.apply {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(activity)
                adapter = launchesAdapter
                launchesAdapter.addItemsToRocketList(it.data!!)
            }
           

        })




    }
    }

