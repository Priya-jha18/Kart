package com.example.servicekartcustomer.myOrders

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.servicekartcustomer.R
import java.util.ArrayList

class RateAdapter(private val context: Context, private val nameList: ArrayList<RateData>) :
    RecyclerView.Adapter<RateAdapter.ViewHolder>() {
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
        RecyclerView.ViewHolder(inflater.inflate(R.layout.adapter_rate, parent, false)) {
        var photo: ImageView = itemView.findViewById(R.id.ivRateDp)
        var name: TextView = itemView.findViewById(R.id.tvRateName)
        var rateId: TextView = itemView.findViewById(R.id.tvRateIdNumber)
        var date: TextView = itemView.findViewById(R.id.tvRateDate)
        var price: TextView = itemView.findViewById(R.id.tvRatePriceCount)
        var count: TextView = itemView.findViewById(R.id.tvRateItemCount)

        fun bind(context: Context, any: RateData) {
            name.text = any.rateName
            rateId.text = any.rateId
            date.text = any.rateDate
            price.text = any.ratePrice
            count.text = any.rateCount
            Glide.with(context)
                .load(any.rateImage)
                .into(photo)

        }


    }
}

