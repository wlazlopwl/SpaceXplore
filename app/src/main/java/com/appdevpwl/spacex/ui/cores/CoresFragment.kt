package com.appdevpwl.spacex.ui.cores

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.appdevpwl.spacex.R
import com.appdevpwl.spacex.data.cores.CoresItem
import com.appdevpwl.spacex.databinding.FragmentCoresBinding
import com.appdevpwl.spacex.util.SnackbarType
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_cores.*
import javax.inject.Inject


class CoresFragment : DaggerFragment() {

    private var _binding: FragmentCoresBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var coresViewModel: CoresViewModel
    lateinit var coresAdapter: CoresAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        AndroidSupportInjection.inject(this)
        coresViewModel =
            ViewModelProviders.of(this, viewModelFactory).get(CoresViewModel::class.java)
        _binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_cores, container, false)
        binding.viewModel = coresViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        coresViewModel.coresLiveData.observe(viewLifecycleOwner, Observer {
            initRecyclerView(it)
        })

        coresViewModel.snackbarText.observe(viewLifecycleOwner, Observer {
            SnackbarType.enableSnackbar(view, it)

        })
        setHasOptionsMenu(true)
    }

    private fun initRecyclerView(data: List<CoresItem>) {
        coresAdapter = CoresAdapter()
        cores_recyclerview.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(activity)
            adapter = coresAdapter
            coresAdapter.addItemsToCoresList(data)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.cores_menu, menu)
        val searchItem = menu.findItem(R.id.cores_menu_search)
        val searchView = searchItem.actionView as SearchView
        searchView.queryHint = "Enter serial"

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                    searchInDB(newText)
                }
                return true
            }

            private fun searchInDB(newText: String) {

                coresViewModel.searchBySerial(newText).observe(viewLifecycleOwner, Observer {
                    coresAdapter.addItemsToCoresList(it)
                })
            }

        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}