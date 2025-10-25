package com.appdevpwl.spacex.ui.rocket

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
import com.appdevpwl.spacex.data.rocket.model.Rocket
import com.appdevpwl.spacex.databinding.FragmentRocketBinding
import com.appdevpwl.spacex.util.SnackbarType
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.DaggerFragment
import javax.inject.Inject


class RocketFragment : DaggerFragment() {
    private var _binding: FragmentRocketBinding? = null
    private val binding get() = _binding!!

    lateinit var rocketAdapter: RocketAdapter

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var rocketViewModel: RocketViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        AndroidSupportInjection.inject(this)
        rocketViewModel =
            ViewModelProviders.of(this, viewModelFactory).get(RocketViewModel::class.java)
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_rocket, container, false)
        binding.viewModel = rocketViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        rocketViewModel.rocketLiveData.observe(viewLifecycleOwner, Observer {
            initRecyclerView(it)
        })
        rocketViewModel.snackbarText.observe(viewLifecycleOwner, Observer {
            SnackbarType.enableSnackbar(view, it)
        })

    }

    private fun initRecyclerView(data: List<Rocket>) {
        rocketAdapter = RocketAdapter()
//        rocket_recyclerview.apply {
//            setHasFixedSize(true)
//            layoutManager = LinearLayoutManager(activity)
//            adapter = rocketAdapter
//            rocketAdapter.addItemsToRocketList(data)
//        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
