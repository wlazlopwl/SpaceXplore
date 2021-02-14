package com.appdevpwl.spacex.ui.launches

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ExpandableListAdapter
import android.widget.ExpandableListView
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.appdevpwl.spacex.R
import com.appdevpwl.spacex.data.launches.model.LaunchesItem
import com.appdevpwl.spacex.databinding.FragmentLaunchesDetailsBinding
import com.appdevpwl.spacex.ui.cores.CoresViewModel
import com.bumptech.glide.Glide
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class LaunchesDetailsFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var coreViewModel: CoresViewModel

    var expandableListView: ExpandableListView? = null
    var adapter: ExpandableListAdapter? = null
    lateinit var launches: LaunchesItem
    private var dataList: HashMap<String, MutableList<List<String>>> = HashMap()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        coreViewModel =
            ViewModelProviders.of(this, viewModelFactory).get(CoresViewModel::class.java)

        val binding: FragmentLaunchesDetailsBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_launches_details, container, false)
        launches = arguments?.get("argLaunchesId") as LaunchesItem

        binding.launchesItem = launches
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        coreViewModel.getAllCoresByLaunchesId(launches.id!!)

        coreViewModel.liveDataCoresByLaunchesId.observe(viewLifecycleOwner, Observer {

            when (it.size) {

                0 -> {
                }
                else -> {
                    val lstGroups: MutableList<String> = ArrayList()
                    lstGroups.add("Cores")
                    val lstContent: MutableList<List<String>> = ArrayList()
                    for (core in it) {

                        val list: List<String> = listOf(core.serial,
                            core.status,
                            core.block.toString(),
                            core.reuse_count.toString(),
                            core.last_update.toString())
                        lstContent.add(list)


                    }
                    dataList[lstGroups[0]] = lstContent




                    expandableListView = view.findViewById(R.id.launches_details_expandable_list)
                    if (expandableListView != null) {
                        adapter =
                            LaunchesExpandableListAdapter(requireContext(), lstGroups, dataList)
                        expandableListView!!.setAdapter(adapter)

                    }
                }
            }
        })


    }

    companion object BindingAdapter {

        @JvmStatic
        @androidx.databinding.BindingAdapter("loadImage")
        fun loadImage(view: ImageView, url: String?) {
            Glide.with(view.context).load(url).error(R.drawable.ic_error_black_24dp)
                .fallback(R.drawable.ic_error_black_24dp).into(view)
        }

    }
}