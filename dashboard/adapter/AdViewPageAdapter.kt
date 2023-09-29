package com.example.servicekartcustomer.dashboard.adapter

import BannerOne
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.servicekartcustomer.R
import com.example.servicekartcustomer.dashboard.model.Viewpagedata
import kotlinx.android.synthetic.main.adapter_ad_viewpager.view.*

class AdViewPageAdapter(
    private val nameList: ArrayList<BannerOne>,
    private val context: Context,
    private val viewPager2: ViewPager2
) : RecyclerView.Adapter<AdViewPageAdapter.ImageViewHolder>() {

    inner class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imageView: ImageView = itemView.findViewById(R.id.ivVPImage)
        fun bind(context: Context, s: Viewpagedata) {
            Glide.with(context)
                .load(s.viewpageImage)
                .into(imageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_ad_viewpager, parent, false)
        return ImageViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val data = nameList[position]
        Glide.with(context)
            .load(data.imageUrl)
            .into(holder.imageView.ivVPImage)

        if (position == nameList.size-1){
            viewPager2.post(runnable)
        }
    }

    override fun getItemCount(): Int {
       return nameList.size
    }
    private val runnable = Runnable {
        nameList.addAll(nameList)
        notifyDataSetChanged()
    }
}