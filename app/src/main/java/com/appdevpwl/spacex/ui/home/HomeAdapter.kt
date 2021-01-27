package com.appdevpwl.spacex.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.appdevpwl.spacex.R
import com.appdevpwl.spacex.databinding.HomeSingleItemBinding

class HomeAdapter : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    private var menuList: List<String> = emptyList()

    class ViewHolder(val binding: HomeSingleItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindView(item: String) {
            binding.apply {
                binding.menuItem = item
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
        holder.bindView(item)

        holder.itemView.setOnClickListener(
            when(item){
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
        menuList=data
        notifyDataSetChanged()

    }
}