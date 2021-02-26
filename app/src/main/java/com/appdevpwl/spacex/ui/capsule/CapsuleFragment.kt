package com.appdevpwl.spacex.ui.capsule

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.appdevpwl.spacex.R
import com.appdevpwl.spacex.data.capsules.Capsule
import com.appdevpwl.spacex.databinding.CapsuleFragmentBinding
import com.appdevpwl.spacex.util.CapsulesSortType
import com.appdevpwl.spacex.util.SnackbarType
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.capsule_fragment.*
import javax.inject.Inject


class CapsuleFragment : DaggerFragment() {
    private var _binding: CapsuleFragmentBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var capsuleViewModel: CapsuleViewModel
    lateinit var capsuleAdapter: CapsuleAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        capsuleAdapter = CapsuleAdapter()
        AndroidSupportInjection.inject(this)
        capsuleViewModel =
            ViewModelProviders.of(this, viewModelFactory).get(CapsuleViewModel::class.java)
        _binding = DataBindingUtil.inflate(inflater, R.layout.capsule_fragment, container, false)
        binding.viewModel = capsuleViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        binding.capsuleRecyclerview.layoutManager = LinearLayoutManager(activity)
        binding.capsuleRecyclerview.adapter = capsuleAdapter
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        capsuleViewModel.allCapsules.observe(viewLifecycleOwner, Observer {
            initRecyclerView(it)


        })
        capsuleViewModel.snackbarText.observe(viewLifecycleOwner, Observer {
            SnackbarType.enableSnackbar(view, it)
        })

        setHasOptionsMenu(true)
    }

    private fun initRecyclerView(data: List<Capsule>) {

        capsule_recyclerview.apply {
            setHasFixedSize(true)
            capsuleAdapter.addCapsuleList(data)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.capsules_menu, menu)


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.capsules_menu_sort_ASC -> capsuleViewModel.sortCapsules(CapsulesSortType.TYPE_ASC)
            R.id.capsules_menu_sort_DESC -> capsuleViewModel.sortCapsules(CapsulesSortType.TYPE_DESC)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
