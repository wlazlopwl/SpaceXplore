package com.appdevpwl.spacex.ui.launches

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.appdevpwl.spacex.R
import com.appdevpwl.spacex.data.launches.model.LaunchesItem
import com.appdevpwl.spacex.databinding.FragmentPastLaunchesBinding
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.DaggerFragment
import javax.inject.Inject
import androidx.recyclerview.widget.RecyclerView


class PastLaunchesFragment : DaggerFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var launchesViewModel: LaunchesViewModel
    private lateinit var launchesAdapter: LaunchesAdapter

    private var _binding: FragmentPastLaunchesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        AndroidSupportInjection.inject(this)
        launchesViewModel =
            ViewModelProvider(this, viewModelFactory).get(LaunchesViewModel::class.java)
        _binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_past_launches, container, false)
        binding.viewModel = launchesViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        launchesAdapter = LaunchesAdapter()
        binding.rvPastLaunches.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(activity)
            adapter = launchesAdapter
        }
        launchesViewModel.pastLaunchesLiveData.observe(viewLifecycleOwner, Observer {
            initRecyclerView(it)
        })
    }

    private fun initRecyclerView(data: List<LaunchesItem>) {
        launchesAdapter.addItemsToLaunchesList(data)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
