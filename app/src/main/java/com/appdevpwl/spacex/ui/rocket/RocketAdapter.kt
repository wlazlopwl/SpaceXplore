package com.appdevpwl.spacex.ui.rocket

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.appdevpwl.spacex.R
import com.appdevpwl.spacex.data.rocket.model.Rocket
import com.appdevpwl.spacex.databinding.RocketSingleItemRvBinding

class RocketAdapter : RecyclerView.Adapter<RocketAdapter.ViewHolder>() {

    private var rocketsList: List<Rocket> = emptyList()

    class ViewHolder(val binding: RocketSingleItemRvBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindView(rocket: Rocket) {
            binding.apply {
                binding.rocketItem = rocket
            }

            itemView.setOnClickListener { view ->
                val argRocketId = rocket.id
                val bundle = bundleOf("argRocketId" to argRocketId)
                view.findNavController()
                    .navigate(R.id.action_nav_rocket_to_rocketDetailsFragment, bundle)
            }

        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        val binding: RocketSingleItemRvBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.rocket_single_item_rv, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RocketAdapter.ViewHolder, position: Int) {
        val rocket = rocketsList[position]
        holder.bindView(rocket)
    }

    override fun getItemCount(): Int {
        return rocketsList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addItemsToRocketList(list: List<Rocket>) {
        rocketsList = list
        notifyDataSetChanged()
    }
}