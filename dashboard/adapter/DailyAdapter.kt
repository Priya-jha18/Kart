package com.example.servicekartcustomer.dashboard.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.servicekartcustomer.R
import com.example.servicekartcustomer.dashboard.model.DailyData

class DailyAdapter(private val context: Context, private val nameList: ArrayList<DailyData>) : RecyclerView.Adapter<DailyAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(context, nameList[position])
    }

    override fun getItemCount(): Int {
        return nameList.size ?: 0
    }

    class ViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.adapter_daily, parent, false)) {
        var image: ImageView = itemView.findViewById(R.id.ivDailyImage)
        var name: TextView = itemView.findViewById(R.id.tvDailyName)
        fun bind(context: Context, any: DailyData) {
            name.text = any.dailyName
            Glide.with(context)
                .load(any.dailyImage)
                .into(image)

        }


    }
}
