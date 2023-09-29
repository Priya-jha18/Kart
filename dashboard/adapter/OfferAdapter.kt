package com.example.servicekartcustomer.dashboard.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.servicekartcustomer.R
import com.example.servicekartcustomer.dashboard.model.OfferData

class OfferAdapter(private val context: Context, private val nameList: ArrayList<OfferData>) : RecyclerView.Adapter<OfferAdapter.ViewHolder>() {
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
        RecyclerView.ViewHolder(inflater.inflate(R.layout.adapter_offer, parent, false)) {
        var image: ImageView = itemView.findViewById(R.id.ivOfferImage)
        var amount: TextView = itemView.findViewById(R.id.tvOfferAmount)
        var name: TextView = itemView.findViewById(R.id.tvOfferName)
        fun bind(context: Context, any: OfferData) {
            name.text = any.offerName
            amount.text = any.offerAmount
            Glide.with(context)
                .load(any.offerImage)
                .into(image)


        }


    }
}