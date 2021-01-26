package com.appdevpwl.spacex.ui.rocket

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

//        private val rocketName: TextView = mView.rocket_name
//        private val rocketFirstFlight: TextView = mView.rocket_first_flight
//        private val rocketStatus: TextView = mView.rocket_status_tv
//        private val rocketImageView: ImageView = mView.rocketIV
//        private val progressBar: ProgressBar = mView.progress_bar
//        private val rocketCompany: TextView = mView.rocket_company
//        private val rocketCost: TextView = mView.rocket_cost_per_launch
//        private val rocketSuccessRate: TextView = mView.rocket_succes_rate

        fun bindView(rocket: Rocket) {
            binding.apply {
                binding.rocketItem = rocket
            }

            itemView.setOnClickListener { view ->
                val argRocketId = rocket.id.toString()
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

//        holder.itemView.setOnClickListener { view ->
//            view.findNavController().navigate(R.id.action_nav_rocket_to_rocketDetailsFragment)
//        }

    }

    override fun getItemCount(): Int {
        return rocketsList.size
    }

    fun addItemsToRocketList(list: List<Rocket>) {
        rocketsList = list
        notifyDataSetChanged()
    }
}