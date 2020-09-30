package com.appdevpwl.spacex.ui.capsule

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager

import com.appdevpwl.spacex.R
import com.appdevpwl.spacex.util.Response
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.capsule_fragment.*
import java.time.LocalDateTime
import javax.inject.Inject

class CapsuleFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var capsuleViewModel: CapsuleViewModel
    lateinit var capsuleAdapter: CapsuleAdapter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        AndroidSupportInjection.inject(this)
        capsuleViewModel =
            ViewModelProviders.of(this, viewModelFactory).get(CapsuleViewModel::class.java)

        return inflater.inflate(R.layout.capsule_fragment, container, false)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)



    }




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        capsuleViewModel.getApiTest()
        capsuleViewModel.getData()
        Log.d("main1", LocalDateTime.now().toString())

        capsuleViewModel.liveData.observe(viewLifecycleOwner, Observer {

            when(it.status){
                Response.Status.SUCCESS -> Toast.makeText(activity, "Działa", Toast.LENGTH_SHORT).show()
            }


            capsuleAdapter = CapsuleAdapter(it.data!!)
            capsule_recyclerview.apply {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(activity)
                adapter = capsuleAdapter
            }


        })



    }




}
