package com.appdevpwl.spacex.ui.rocket

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.appdevpwl.spacex.R
import com.appdevpwl.spacex.data.rocket.model.Rocket
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import kotlinx.android.synthetic.main.rocket_single_item_rv.view.*

class RocketAdapter : RecyclerView.Adapter<RocketAdapter.ViewHolder>() {

    private var rocketsList: List<Rocket> = emptyList()

    class ViewHolder(private val mView: View) : RecyclerView.ViewHolder(mView) {

        private val rocketName: TextView = mView.rocket_name
        private val rocketFirstFlight: TextView = mView.rocket_first_flight
        private val rocketStatus: TextView = mView.rocket_status_tv
        private val rocketImageView: ImageView = mView.rocketIV
        private val progressBar: ProgressBar = mView.progress_bar
        private val rocketCompany: TextView = mView.rocket_company
        private val rocketCost:TextView = mView.rocket_cost_per_launch
        private val rocketSuccessRate:TextView = mView.rocket_succes_rate

        fun bindView(rocket: Rocket) {
            rocketName.text = rocket.rocket_name
            rocketFirstFlight.text = rocket.first_flight
            rocketStatus.text = rocket.active.toString()

            Glide.with(mView).load(rocket.flickr_images[0])
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        progressBar.visibility = View.VISIBLE
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        progressBar.visibility = View.GONE
                        return false
                    }
                })
                .error(R.drawable.ic_error_black_24dp)
                .fallback(R.drawable.ic_error_black_24dp)
                .centerCrop()
                .into(rocketImageView)
            rocketCompany.text = rocket.company
            rocketCost.text= rocket.cost_per_launch.toString()
            rocketSuccessRate.text=rocket.success_rate_pct.toString()+" %"

           itemView.setOnClickListener{view->
               val argRocketId = rocket.id.toString()
               val bundle = bundleOf("argRocketId" to argRocketId)
               view.findNavController().navigate(R.id.action_nav_rocket_to_rocketDetailsFragment, bundle)
           }

        }

//        private fun transformCost(costPerLaunch: Int): String? {
//            val costPerLaunch = costPerLaunch.toString()
//            val costCharSize = costPerLaunch.length
//            val numberOfSpace =(costCharSize/3)-1
//
//        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.rocket_single_item_rv, parent, false)
    )

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