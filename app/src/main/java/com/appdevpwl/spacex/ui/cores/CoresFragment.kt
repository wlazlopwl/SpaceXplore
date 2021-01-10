package com.appdevpwl.spacex.ui.cores

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.appdevpwl.spacex.R
import com.appdevpwl.spacex.data.cores.CoresItem
import com.appdevpwl.spacex.util.Response
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_cores.*
import javax.inject.Inject

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class CoresFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var coresViewModel: CoresViewModel

    lateinit var coresAdapter: CoresAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        AndroidSupportInjection.inject(this)
        coresViewModel =
            ViewModelProviders.of(this, viewModelFactory).get(CoresViewModel::class.java)
        return inflater.inflate(R.layout.fragment_cores, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        coresViewModel.getDataFromApi()
        coresViewModel.liveData.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Response.Status.SUCCESS -> {
                    cores_progressBar.visibility = View.INVISIBLE
                    initialRecyclerView(it)

                }
                Response.Status.LOADING -> cores_progressBar.visibility = View.VISIBLE
            }


        })
    }

    private fun initialRecyclerView(data: Response<List<CoresItem>>) {
        coresAdapter = CoresAdapter()
        cores_recyclerview.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(activity)
            adapter = coresAdapter
            coresAdapter.addItemsToCoresList(data.data!!)
        }
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CoresFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}