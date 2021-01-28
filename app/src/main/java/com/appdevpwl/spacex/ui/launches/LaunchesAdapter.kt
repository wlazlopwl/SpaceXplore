package com.appdevpwl.spacex.ui.launches

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.appdevpwl.spacex.R
import com.appdevpwl.spacex.data.launches.model.LaunchesItem
import com.appdevpwl.spacex.util.convertUnixTime
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import kotlinx.android.synthetic.main.launches_single_item_rv.view.*


class LaunchesAdapter : RecyclerView.Adapter<LaunchesAdapter.ViewHolder>() {

    private var launchesList: List<LaunchesItem> = emptyList()

    class ViewHolder(private val mView: View) : RecyclerView.ViewHolder(mView) {

        private val launchesName: TextView = mView.launches_name
        private val launchDate: TextView = mView.launch_date
        private val launchImageView: ImageView = mView.launch_img
        private val launchFlightNumber: TextView = mView.launch_flight_number
        private val progressBar = mView.progressBar


        fun bindView(launchesItem: LaunchesItem) {
            launchesName.text = launchesItem.name
            launchDate.text = convertUnixTime(launchesItem.date_unix!!)
            launchFlightNumber.text = launchesItem.flight_number.toString()
            Glide.with(mView).load(launchesItem.links?.patch?.small)
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean,
                    ): Boolean {
                        progressBar.visibility = View.GONE
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean,
                    ): Boolean {
                        progressBar.visibility = View.GONE
                        return false
                    }
                })
                .error(R.drawable.ic_error_black_24dp)
                .fallback(R.drawable.ic_error_black_24dp)
                .into(launchImageView)



            itemView.setOnClickListener { view ->
                val argLaunchesId = launchesItem
                val bundle = bundleOf("argLaunchesId" to argLaunchesId)
                view.findNavController()
                    .navigate(R.id.action_nav_launches_to_launchesDetailsFragment, bundle)
            }

        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.launches_single_item_rv, parent, false)
    )

    override fun onBindViewHolder(holder: LaunchesAdapter.ViewHolder, position: Int) {
        val launchesItem = launchesList[position]
        holder.bindView(launchesItem)


    }

    override fun getItemCount(): Int {
        return launchesList.size
    }

    fun addItemsToLaunchesList(list: List<LaunchesItem>) {
        launchesList = list
        notifyDataSetChanged()
    }
}