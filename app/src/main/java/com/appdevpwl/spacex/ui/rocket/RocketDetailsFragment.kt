package com.appdevpwl.spacex.ui.rocket

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.preference.Preference
import androidx.preference.PreferenceManager
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.appdevpwl.spacex.R
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.DaggerFragment
import javax.inject.Inject


class RocketDetailsFragment : DaggerFragment() {
    lateinit var listViewAdapter: RocketDetailExpandableAdapter
    lateinit var listViewAdapter1: RocketDetailExpandableAdapter
    lateinit var listViewAdapter2: RocketDetailExpandableAdapter
    lateinit var groupList: List<String>
    lateinit var groupList1: List<String>
    lateinit var groupList2: List<String>
    lateinit var groupList3: List<String>
    lateinit var childList: HashMap<String, List<String>>
    lateinit var viewPager2: ViewPager2
    lateinit var viewPager: ViewPager
    lateinit var viewPagerAdapter: RocketDetailsPagerAdapter
//first card
    private lateinit var rocketName: TextView
    private lateinit var rocketCost: TextView
    private lateinit var rocketFirstFlight: TextView
    private lateinit var rocketStages: TextView
    private lateinit var rocketSuccessRate: TextView
    private lateinit var rocketStatusImg: ImageView
//    metrics card
    private lateinit var rocketHeight: TextView
    private lateinit var rocketDiametetr: TextView
    private lateinit var rocketMass: TextView


    @Inject
    lateinit var sharedPreferences: SharedPreferences



    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: RocketDetailsViewModel

    companion object {
        fun newInstance() = RocketDetailsFragment()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        AndroidSupportInjection.inject(this)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(RocketDetailsViewModel::class.java)
        return inflater.inflate(R.layout.rocket_details_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        val prefEditor = sharedPreferences.edit()
//        prefEditor.putString("lengthUnit", "meters")
//        prefEditor.putString("massUnit", "kg")
//        prefEditor.apply()


        rocketName = view.findViewById(R.id.rocket_details_name)
        rocketCost = view.findViewById(R.id.rocket_details_cost)
        rocketFirstFlight = view.findViewById(R.id.rocket_details_first_flight)
        rocketStages = view.findViewById(R.id.rocket_details_stages)
        rocketSuccessRate = view.findViewById(R.id.rocket_details_success_rate)
        rocketStatusImg = view.findViewById(R.id.rocket_details_status_IV)

        rocketMass = view.findViewById(R.id.rocket_details_metric_mass)
        rocketHeight = view.findViewById(R.id.rocket_details_metric_height)
        rocketDiametetr = view.findViewById(R.id.rocket_details_metric_diameter)







        viewPager = requireView().findViewById(R.id.rocket_img_view_pager)


        val id = arguments?.getString("argRocketId")!!.toInt()

        viewModel.getRocketById(id)

        viewModel.rocketLiveData.observe(viewLifecycleOwner, Observer {
//            Toolbar title
            (activity as AppCompatActivity?)!!.supportActionBar!!.title = it.rocket_name

//            first card - MAIN info
            rocketName.text = it.rocket_name
            rocketCost.text = it.cost_per_launch.toString()
            rocketFirstFlight.text = it.first_flight
            rocketStages.text = it.stages.toString()
            rocketSuccessRate.text = it.success_rate_pct.toString()
            when (it.active) {
                true -> {
                    rocketStatusImg.setImageResource(R.drawable.ic_baseline_trip_origin_24_green)
                }
                else -> {
                    rocketStatusImg.setImageResource(R.drawable.ic_baseline_trip_origin_24_red)
                }
            }

//            val sp=PreferenceManager.getDefaultSharedPreferences(this.context)
//            val lengthU= sp.getString("lengthUnit","")
//
//            second card - METRICS
            when (sharedPreferences.getString("lengthUnit", "meters")) {
                "meters" -> {
                    rocketDiametetr.text = getString(R.string.concatenate_double_string,
                        it.diameter?.meters,"m")
                    rocketHeight.text = getString(R.string.concatenate_double_string,
                        it.height?.meters,"m")
                }
                else -> {
                    rocketDiametetr.text = getString(R.string.concatenate_double_string,
                        it.diameter?.feet,"ft")
                    rocketHeight.text = getString(R.string.concatenate_double_string,
                        it.height?.feet,"ft")
                }
            }
            when (sharedPreferences.getString("massUnit", "kg")){

                "kg" -> rocketMass.text = getString(R.string.concatenate_int_string,
                    it.mass?.kg,"kg")
                else -> rocketMass.text = getString(R.string.concatenate_int_string, it.mass?.lb,"lb")
            }



            ///////////////////////////////


            var urlList: ArrayList<String> = ArrayList()
            for (i in it.flickr_images) {
                urlList.add(i)
            }

            viewPagerAdapter = RocketDetailsPagerAdapter(requireContext(), urlList)
            viewPager.adapter = viewPagerAdapter


        })
    }

}
