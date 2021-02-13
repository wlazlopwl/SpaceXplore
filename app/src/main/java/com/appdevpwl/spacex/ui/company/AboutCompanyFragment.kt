package com.appdevpwl.spacex.ui.company

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.appdevpwl.spacex.R
import com.appdevpwl.spacex.databinding.FragmentAboutCompanyBinding
import com.appdevpwl.spacex.util.SnackbarType
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_about_company.*
import javax.inject.Inject


class AboutCompanyFragment : DaggerFragment() {
    private var _binding: FragmentAboutCompanyBinding? = null
    private val binding get() = _binding!!
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var companyViewModel: AboutCompanyViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        AndroidSupportInjection.inject(this)
        companyViewModel =
            ViewModelProviders.of(this, viewModelFactory).get(AboutCompanyViewModel::class.java)
        _binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_about_company,
            container,
            false)
        binding.viewmodel = companyViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        companyViewModel.snackbarText.observe(viewLifecycleOwner, Observer {
            SnackbarType.enableSnackbar(view, it)

        })

        showOnMap.setOnClickListener {
            val mapUri = Uri.parse("geo:0,0?q=" + Uri.encode(companyViewModel.getCompanyAddress()))
            val mapIntent = Intent(Intent.ACTION_VIEW, mapUri)
            mapIntent.setPackage("com.google.android.apps.maps")
            startActivity(mapIntent)
        }


    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}