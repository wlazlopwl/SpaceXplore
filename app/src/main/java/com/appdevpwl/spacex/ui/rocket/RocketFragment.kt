package com.appdevpwl.spacex.ui.rocket

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.appdevpwl.spacex.R
import com.appdevpwl.spacex.util.Response
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_rocket.*
import javax.inject.Inject


class RocketFragment : DaggerFragment() {
     lateinit var rocketAdapter: RocketAdapter

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var rocketViewModel: RocketViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        AndroidSupportInjection.inject(this)
        rocketViewModel =
            ViewModelProviders.of(this, viewModelFactory).get(RocketViewModel::class.java)
        return inflater.inflate(R.layout.fragment_rocket, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rocketViewModel.getDataFromApi()
        rocketViewModel.liveData.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Response.Status.SUCCESS -> {
                    rocket_progressBar.visibility = View.INVISIBLE
                    rocketAdapter = RocketAdapter()
                    rocket_recyclerview.apply {
                        setHasFixedSize(true)
                        layoutManager = LinearLayoutManager(activity)
                        adapter = rocketAdapter
                    }
                    it.data?.let { it1 -> rocketAdapter.addItemsToRocketList(it1) }
                }

                Response.Status.ERROR -> Toast.makeText(context, it.message, Toast.LENGTH_SHORT)
                    .show()
                Response.Status.LOADING -> {
                    rocket_progressBar.visibility = View.VISIBLE
                }
            }


        })
    }

    companion object {


        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RocketFragment().apply {
            }
    }
}
