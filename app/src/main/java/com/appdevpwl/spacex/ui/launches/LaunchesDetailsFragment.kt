package com.appdevpwl.spacex.ui.launches

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ExpandableListAdapter
import android.widget.ExpandableListView
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.appdevpwl.spacex.R
import com.appdevpwl.spacex.data.launches.model.LaunchesItem
import com.appdevpwl.spacex.databinding.FragmentLaunchesDetailsBinding
import com.bumptech.glide.Glide

class LaunchesDetailsFragment : Fragment() {

    var expandableListView: ExpandableListView? = null
    var adapter: ExpandableListAdapter? = null

    private var dataList: HashMap<String, List<String>> = HashMap()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentLaunchesDetailsBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_launches_details, container, false)
        val launches: LaunchesItem = arguments?.get("argLaunchesId") as LaunchesItem

        binding.launchesItem = launches
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        val lstGroups: MutableList<String> = ArrayList()
        lstGroups.add("Cores")
        lstGroups.add("Payloads")

        val lstContent: MutableList<String> = ArrayList()
        lstContent.add("Renda Variavél")
        lstContent.add("Renda Fixa")
        lstContent.add("Extrato")
        lstContent.add("Renda Variavél")
        lstContent.add("Renda Fixa")
        lstContent.add("Extrato")

        val lstContent1: MutableList<String> = ArrayList()
        lstContent1.add("Renda Variavél")
        lstContent1.add("Renda Fixa")
        lstContent1.add("Extrato")
        lstContent1.add("Renda Variavél")
        lstContent1.add("Renda Fixa")
        lstContent1.add("Extrato")

        dataList.put(lstGroups.get(0), lstContent)
        dataList.put(lstGroups.get(1), lstContent1)



        expandableListView = view.findViewById(R.id.launches_details_expandable_list)
        if (expandableListView != null) {
            adapter = LaunchesExpandableListAdapter(requireContext(), lstGroups, dataList)
            expandableListView!!.setAdapter(adapter)

        }


    }

    companion object BindingAdapter {

        @JvmStatic
        @androidx.databinding.BindingAdapter("loadImage")
        fun loadImage(view: ImageView, url: String) {
            Glide.with(view.context).load(url).into(view)
        }

    }
}