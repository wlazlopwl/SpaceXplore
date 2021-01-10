package com.appdevpwl.spacex.ui.launches

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ExpandableListAdapter
import android.widget.ExpandableListView
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
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
   lateinit var launches : LaunchesItem
    private var dataList: HashMap<String, List<String>> = HashMap()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        coreViewModel= ViewModelProviders.of(this, viewModelFactory).get(CoresViewModel::class.java)

        val binding: FragmentLaunchesDetailsBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_launches_details, container, false)
        launches = arguments?.get("argLaunchesId") as LaunchesItem

        binding.launchesItem = launches
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        coreViewModel.getAllCores()
        coreViewModel.result.observe(viewLifecycleOwner, Observer {



        })

        val lstGroups: MutableList<String> = ArrayList()

//        lstGroups.add("Payloads")
        coreViewModel.getAllCoresByLaunchesId(launches.id!!)

        coreViewModel.liveDataCoresByLaunchesId.observe(viewLifecycleOwner, Observer {
            when(it.size){

                0 -> {}
                else -> {
                    lstGroups.add("Cores")
                    val lstContent: MutableList<String> = ArrayList()
                    for (core in it){


                        lstContent.add(core.serial)
                        lstContent.add(core.id)



                    }
                    dataList[lstGroups[0]] = lstContent
                }
            }
        })



        val lstContent1: MutableList<String> = ArrayList()
        lstContent1.add("Renda Variavél")
        lstContent1.add("Renda Fixa")



//        dataList.put(lstGroups.get(1), lstContent1)


        expandableListView = view.findViewById(R.id.launches_details_expandable_list)
        if (expandableListView != null) {
            adapter = LaunchesExpandableListAdapter(requireContext(), lstGroups, dataList)
            expandableListView!!.setAdapter(adapter)

        }



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