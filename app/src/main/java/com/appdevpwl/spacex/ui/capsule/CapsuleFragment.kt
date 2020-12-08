package com.appdevpwl.spacex.ui.capsule

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.observe
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.appdevpwl.spacex.R
import com.appdevpwl.spacex.util.CapsulesSortType
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.capsule_fragment.*
import javax.inject.Inject


class CapsuleFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var capsuleViewModel: CapsuleViewModel
    lateinit var capsuleAdapter: CapsuleAdapter
    lateinit var bottomSheetMain: LinearLayout
    lateinit var v: View
    var i = 0


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        AndroidSupportInjection.inject(this)
        capsuleViewModel =
            ViewModelProviders.of(this, viewModelFactory).get(CapsuleViewModel::class.java)

        v = inflater.inflate(R.layout.capsule_fragment, container, false)
        bottomSheetMain = v.findViewById(R.id.sort_main_linearlayout)

        bottomSheetMain.setOnClickListener { v ->
            Navigation.findNavController(v).navigate(R.id.bottomSheetMainSortFragment)
        }



        return v

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        capsuleViewModel.getDataFromApiAndSave()

        val navController = findNavController()
        navController.currentBackStackEntry?.savedStateHandle?.getLiveData<String>("sort_by")
            ?.observe(viewLifecycleOwner) {
                when (it) {
                    "TYPE_ASC" -> capsuleViewModel.sortCapsules(CapsulesSortType.TYPE_ASC)
                    "TYPE_DESC" -> capsuleViewModel.sortCapsules(CapsulesSortType.TYPE_DESC)
                    "TIME_ASC" -> capsuleViewModel.sortCapsules(CapsulesSortType.TIME_ASC)
                    "TIME_DESC" -> capsuleViewModel.sortCapsules(CapsulesSortType.TIME_DESC)

                }


            }

        capsuleViewModel.allCapsules.observe(viewLifecycleOwner, Observer {

            i++
            Toast.makeText(activity, i.toString(), Toast.LENGTH_SHORT).show()
//            when (it.status) {
//                Response.Status.SUCCESS -> Toast.makeText(activity, "Działa", Toast.LENGTH_SHORT).show()
//            }
            capsuleAdapter = CapsuleAdapter()
            capsule_recyclerview.apply {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(activity)
                adapter = capsuleAdapter

            }


            capsuleAdapter.addCapsuleList(it)


        })


    }


}
