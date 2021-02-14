package com.appdevpwl.spacex.ui.cores

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.appdevpwl.spacex.R
import com.appdevpwl.spacex.data.cores.CoresItem
import com.appdevpwl.spacex.data.launches.model.Core
import com.appdevpwl.spacex.databinding.FragmentCoresDetailsBinding
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.DaggerFragment
import javax.inject.Inject


class CoresDetailsFragment : DaggerFragment() {

    private var _binding: FragmentCoresDetailsBinding?=null
    val binding get() = _binding!!

    private var _coresItem: CoresItem?= null
    val coresItem get() = _coresItem

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var coresViewModel: CoresViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _coresItem = arguments?.get("coresItem") as CoresItem
        AndroidSupportInjection.inject(this)
        coresViewModel =
            ViewModelProviders.of(this, viewModelFactory).get(CoresViewModel::class.java)
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_cores_details, container, false)
        binding.coresItem = coresItem

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }


}