package com.appdevpwl.spacex.ui.cores

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.appdevpwl.spacex.R
import com.appdevpwl.spacex.data.cores.CoresItem
import com.appdevpwl.spacex.databinding.CoreSingleItemRvBinding
import kotlinx.android.synthetic.main.core_single_item_rv.view.*
import kotlinx.android.synthetic.main.launches_single_item_rv.view.*

class CoresAdapter : RecyclerView.Adapter<CoresAdapter.ViewHolder>() {

    private var coresList: List<CoresItem> = emptyList()

    class ViewHolder(val binding: CoreSingleItemRvBinding) : RecyclerView.ViewHolder(binding.root) {

//        private val coreSerial : TextView = mView.core_serial_type
//        private val coreBlock : TextView = mView.core_blocks_tv
//        private val coreStatus : TextView = mView.core_status_tv
//        private val coreReuseCount : TextView = mView.core_reuse_count_tv
//        private val coreLastUpdate:TextView=mView.cores_last_update
        fun bindView(coresItem: CoresItem) {
            binding.apply {
                binding.coresItem = coresItem
            }

//            coreSerial.text=coresItem.serial
//            when(coresItem.block){
//                null -> coreBlock.text="No data"
//                else -> coreBlock.text=coresItem.block.toString()
//            }
//
//            coreStatus.text=coresItem.status
//            coreReuseCount.text=coresItem.reuse_count.toString()
//            coreLastUpdate.text=coresItem.last_update
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: CoreSingleItemRvBinding = DataBindingUtil.inflate(layoutInflater,
            R.layout.core_single_item_rv,
            parent, false)
        return ViewHolder(binding)

//        LayoutInflater.from(parent.context).inflate(R.layout.core_single_item_rv, parent, false)
    }





    override fun onBindViewHolder(holder: CoresAdapter.ViewHolder, position: Int) {
        val coresItem = coresList[position]
        holder.bindView(coresItem)
    }

    override fun getItemCount(): Int {
        return coresList.size
    }

    fun addItemsToCoresList(list: List<CoresItem>) {
        coresList = list
        notifyDataSetChanged()
    }
}