package com.appdevpwl.spacex.ui.cores

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.appdevpwl.spacex.R
import com.appdevpwl.spacex.data.cores.CoresItem
import com.appdevpwl.spacex.databinding.FragmentCoresBinding
import com.appdevpwl.spacex.util.SnackbarType
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_cores.*
import kotlinx.coroutines.launch
import javax.inject.Inject


class CoresFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var coresViewModel: CoresViewModel
    lateinit var swipeRefresh: SwipeRefreshLayout
    lateinit var coresAdapter: CoresAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        AndroidSupportInjection.inject(this)
        coresViewModel =
            ViewModelProviders.of(this, viewModelFactory).get(CoresViewModel::class.java)
        val binding: FragmentCoresBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_cores, container, false)
        binding.viewModel = coresViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        swipeRefresh = view.findViewById(R.id.cores_refresh)
        coresViewModel.liveData.observe(viewLifecycleOwner, Observer {
            initRecyclerView(it)
        })

        coresViewModel.snackbarText.observe(viewLifecycleOwner, Observer {
            SnackbarType.enableSnackbar(view, it)

        })


        swipeRefresh.setOnRefreshListener {
            viewLifecycleOwner.lifecycleScope.launch {
                coresViewModel.refreshData()
                swipeRefresh.isRefreshing = false
            }

        }


    }


    private fun initRecyclerView(data: List<CoresItem>) {


        coresAdapter = CoresAdapter()
        cores_recyclerview.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(activity)
            adapter = coresAdapter
            coresAdapter.addItemsToCoresList(data)
        }
    }
}