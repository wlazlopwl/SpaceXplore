package com.appdevpwl.spacex.ui.capsule


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.appdevpwl.spacex.R
import com.appdevpwl.spacex.data.capsules.Capsule
import com.appdevpwl.spacex.databinding.CapsuleSingleItemRvBinding


class CapsuleAdapter : RecyclerView.Adapter<CapsuleAdapter.ViewHolder>() {

    private var capsuleList: List<Capsule> = emptyList()

    class ViewHolder(val binding: CapsuleSingleItemRvBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindView(capsule: Capsule) {
            binding.apply {
                binding.capsuleItem = capsule
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val binding: CapsuleSingleItemRvBinding =
            DataBindingUtil.inflate(inflater, R.layout.capsule_single_item_rv, parent, false)
        return ViewHolder(binding)

    }

    override fun onBindViewHolder(holder: CapsuleAdapter.ViewHolder, position: Int) {
        val capsule = capsuleList[position]
        holder.bindView(capsule)
    }

    override fun getItemCount(): Int {
        return capsuleList.size
    }

    fun addCapsuleList(list: List<Capsule>) {
        this.capsuleList = list
        notifyDataSetChanged()

    }
}