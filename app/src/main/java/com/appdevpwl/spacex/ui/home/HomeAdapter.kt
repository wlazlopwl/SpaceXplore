package com.appdevpwl.spacex.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.appdevpwl.spacex.R
import com.appdevpwl.spacex.databinding.HomeSingleItemBinding


class HomeAdapter : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    private var menuList: List<String> = emptyList()

    class ViewHolder(val binding: HomeSingleItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindView(item: String, imageId: Int) {
            binding.apply {
                binding.menuItem = item
                binding.imageId =imageId
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: HomeSingleItemBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.home_single_item, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeAdapter.ViewHolder, position: Int) {
        val item = menuList[position]
        val imageId= when(item){
            "All launches" -> R.drawable.launches1
            "Cores" -> R.drawable.cores
            "Capsules" -> R.drawable.capsule1
            "Rockets" -> R.drawable.rocket_icon1
            else -> null
        }

        if (imageId != null) {
            holder.bindView(item, imageId)
        }


        holder.itemView.setOnClickListener(
            when (item) {
                "All launches" -> Navigation.createNavigateOnClickListener(R.id.nav_launches)
                "Cores" -> Navigation.createNavigateOnClickListener(R.id.nav_cores)
                "Capsules" -> Navigation.createNavigateOnClickListener(R.id.nav_capsule)
                "Rockets" -> Navigation.createNavigateOnClickListener(R.id.nav_rocket)
                else -> null
            }

        )
    }

    override fun getItemCount(): Int {
        return menuList.size
    }

    fun addMenuItemToList(data: List<String>) {
        menuList = data
        notifyDataSetChanged()

    }

}