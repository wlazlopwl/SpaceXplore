package com.appdevpwl.spacex.ui.rocket

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import androidx.viewpager.widget.PagerAdapter
import com.appdevpwl.spacex.R
import com.appdevpwl.spacex.util.GlideApp
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.rocket_slide_img.view.*

class RocketDetailsPagerAdapter(private val context: Context, private val urlList: List<String>) : PagerAdapter() {

    lateinit var inflater: LayoutInflater
    lateinit var progressBar: ProgressBar
    override fun getCount(): Int {
       return urlList.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {


        return view === `object`
    }
    override fun instantiateItem(container: ViewGroup, position: Int): Any {



        var url = urlList[position]
        var image:ImageView
        inflater=context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var view:View=inflater.inflate(R.layout.rocket_slide_img, container,false)
        image= view.findViewById(R.id.image_slide_item_IV)
//        progressBar=view.findViewById(R.id.rocket_details_img_progressbar)
//        Picasso.get().load(url).into(image);

        GlideApp.with(context).load(url)
            .listener(object : RequestListener<Drawable>{
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
//                    progressBar.visibility= View.VISIBLE
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
//                    progressBar.visibility= View.INVISIBLE
                    return false
                }

            } )
            .centerCrop()
            .placeholder(android.R.drawable.progress_indeterminate_horizontal)
            .error(R.drawable.ic_error_black_24dp)
            .fallback(R.drawable.ic_error_black_24dp)
            .into(image)
        container.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
       container.removeView(`object` as LinearLayout)
    }
}