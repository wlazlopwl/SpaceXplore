package com.appdevpwl.spacex.ui.rocket

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.datastore.core.DataStore
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.asLiveData
import androidx.viewpager.widget.ViewPager
import com.appdevpwl.spacex.R
import com.appdevpwl.spacex.data.DataStorePreferences
import com.appdevpwl.spacex.databinding.RocketDetailsFragmentBinding
import com.appdevpwl.spacex.util.Constant.Companion.MASS_UNIT
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.DaggerFragment
import javax.inject.Inject


class RocketDetailsFragment : DaggerFragment() {
    private var _binding: RocketDetailsFragmentBinding? = null
    private val binding get() = _binding!!

    lateinit var viewPager: ViewPager
    lateinit var viewPagerAdapter: RocketDetailsPagerAdapter


    //    metrics card
    private lateinit var rocketHeight: TextView
    private lateinit var rocketDiametetr: TextView
    private lateinit var rocketMass: TextView

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

        rocketMass = view.findViewById(R.id.rocket_details_metric_mass)
        rocketHeight = view.findViewById(R.id.rocket_details_metric_height)
        rocketDiametetr = view.findViewById(R.id.rocket_details_metric_diameter)

        viewPager = requireView().findViewById(R.id.rocket_img_view_pager)


        val id = arguments?.getString("argRocketId")!!

        viewModel.getRocketById(id)

        viewModel.rocketLiveData.observe(viewLifecycleOwner, Observer {
//            Toolbar title
            (activity as AppCompatActivity?)!!.supportActionBar!!.title = it.name


//

//            second card - METRICS
//            when (sharedPreferences.getString("lengthUnit", "meters")) {
//                "meters" -> {
//                    rocketDiametetr.text = getString(R.string.concatenate_double_string,
//                        it.diameter.meters, "m")
//                    rocketHeight.text = getString(R.string.concatenate_double_string,
//                        it.height.meters, "m")
//                }
//                else -> {
//                    rocketDiametetr.text = getString(R.string.concatenate_double_string,
//                        it.diameter.feet, "ft")
//                    rocketHeight.text = getString(R.string.concatenate_double_string,
//                        it.height.feet, "ft")
//                }
//            }
//


            var urlList: ArrayList<String> = ArrayList()
            for (i in it.flickr_images) {
                urlList.add(i)
            }

            viewPagerAdapter = RocketDetailsPagerAdapter(requireContext(), urlList)
            viewPager.adapter = viewPagerAdapter


        })
    }

}
