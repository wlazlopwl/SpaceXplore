package com.appdevpwl.spacex.ui.rocket

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.appdevpwl.spacex.R
import com.appdevpwl.spacex.util.GlideApp
import com.bumptech.glide.Glide
import com.makeramen.roundedimageview.RoundedImageView

class RocketImgSliderAdapter(private val urlList: List<String>, val viewPager2: ViewPager2) :
    RecyclerView.Adapter<RocketImgSliderAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var image: ImageView = itemView.findViewById<ImageView>(R.id.image_slide_item_IV)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.rocket_slide_img, parent, false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        GlideApp.with(holder.itemView.context).load(urlList[position])
            .centerCrop()
            .placeholder(android.R.drawable.progress_indeterminate_horizontal)
            .error(android.R.drawable.stat_notify_error).into(holder.image)



    }

    override fun getItemCount(): Int {
        return urlList.size
    }
}