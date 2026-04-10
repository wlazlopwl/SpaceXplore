package com.appdevpwl.spacex.ui.launches

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.appdevpwl.spacex.R
import com.appdevpwl.spacex.data.launches.model.LaunchesItem
import com.appdevpwl.spacex.databinding.LaunchesSingleItemRvBinding
import com.appdevpwl.spacex.util.convertUnixTime
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target


class LaunchesAdapter : RecyclerView.Adapter<LaunchesAdapter.ViewHolder>() {

    private var launchesList: List<LaunchesItem> = emptyList()

    class ViewHolder(private val binding: LaunchesSingleItemRvBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindView(launchesItem: LaunchesItem) {
            binding.launchesName.text = launchesItem.name ?: ""
            binding.launchDate.text = launchesItem.date_unix?.let { convertUnixTime(it) } ?: ""
            binding.launchFlightNumber.text = launchesItem.flight_number?.toString() ?: ""
            binding.progressBar.visibility = View.VISIBLE

            Glide.with(binding.root)
                .load(launchesItem.links?.patch?.small)
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>,
                        isFirstResource: Boolean,
                    ): Boolean {
                        binding.progressBar.visibility = View.GONE
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable,
                        model: Any,
                        target: Target<Drawable>?,
                        dataSource: DataSource,
                        isFirstResource: Boolean,
                    ): Boolean {
                        binding.progressBar.visibility = View.GONE
                        return false
                    }
                })
                .error(R.drawable.ic_error_black_24dp)
                .fallback(R.drawable.ic_error_black_24dp)
                .into(binding.launchImg)

            binding.root.setOnClickListener { view ->
                val bundle = bundleOf("argLaunchesId" to launchesItem)
                view.findNavController()
                    .navigate(R.id.action_nav_launches_to_launchesDetailsFragment, bundle)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = LaunchesSingleItemRvBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LaunchesAdapter.ViewHolder, position: Int) {
        val launchesItem = launchesList[position]
        holder.bindView(launchesItem)
    }

    override fun getItemCount(): Int {
        return launchesList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addItemsToLaunchesList(list: List<LaunchesItem>) {
        launchesList = list
        notifyDataSetChanged()
    }
}