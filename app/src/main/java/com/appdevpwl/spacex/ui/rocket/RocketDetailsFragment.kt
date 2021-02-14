package com.appdevpwl.spacex.ui.rocket

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager.widget.ViewPager
import com.appdevpwl.spacex.R
import com.appdevpwl.spacex.data.DataStorePreferences
import com.appdevpwl.spacex.databinding.RocketDetailsFragmentBinding
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.DaggerFragment
import javax.inject.Inject


class RocketDetailsFragment : DaggerFragment() {
    private var _binding: RocketDetailsFragmentBinding? = null
    private val binding get() = _binding!!

    lateinit var viewPager: ViewPager
    lateinit var viewPagerAdapter: RocketDetailsPagerAdapter


    @Inject
    lateinit var preferences: DataStorePreferences


    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: RocketDetailsViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        AndroidSupportInjection.inject(this)
        viewModel =
            ViewModelProviders.of(this, viewModelFactory).get(RocketDetailsViewModel::class.java)
        _binding =
            DataBindingUtil.inflate(inflater, R.layout.rocket_details_fragment, container, false)
        binding.viewmodel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        viewPager = requireView().findViewById(R.id.rocket_img_view_pager)


        val id = arguments?.getString("argRocketId")!!

        viewModel.getRocketById(id)

        viewModel.rocketLiveData.observe(viewLifecycleOwner, Observer {
//            Toolbar title
            (activity as AppCompatActivity?)!!.supportActionBar!!.title = it.name


            var urlList: ArrayList<String> = ArrayList()
            for (i in it.flickr_images) {
                urlList.add(i)
            }

            viewPagerAdapter = RocketDetailsPagerAdapter(requireContext(), urlList)
            viewPager.adapter = viewPagerAdapter


        })
    }

}
