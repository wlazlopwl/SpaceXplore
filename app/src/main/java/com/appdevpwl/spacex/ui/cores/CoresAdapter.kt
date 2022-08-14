package com.appdevpwl.spacex.ui.cores

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.appdevpwl.spacex.R
import com.appdevpwl.spacex.data.cores.CoresItem
import com.appdevpwl.spacex.databinding.CoreSingleItemRvBinding

class CoresAdapter : RecyclerView.Adapter<CoresAdapter.ViewHolder>() {

    private var coresList: List<CoresItem> = emptyList()

    class ViewHolder(val binding: CoreSingleItemRvBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bindView(coresItem: CoresItem) {
            binding.apply {
                binding.coresItem = coresItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: CoreSingleItemRvBinding = DataBindingUtil.inflate(layoutInflater,
            R.layout.core_single_item_rv,
            parent, false)
        return ViewHolder(binding)
    }


    override fun onBindViewHolder(holder: CoresAdapter.ViewHolder, position: Int) {
        val coresItem = coresList[position]
        holder.bindView(coresItem)
        holder.itemView.setOnClickListener { view ->
            val coresItem = coresItem
            val bundle = bundleOf("coresItem" to coresItem)
            view.findNavController()
                .navigate(R.id.action_nav_cores_to_coresDetailsFragment, bundle)
        }
    }

    override fun getItemCount(): Int {
        return coresList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addItemsToCoresList(list: List<CoresItem>) {
        coresList = list
        notifyDataSetChanged()
    }
}