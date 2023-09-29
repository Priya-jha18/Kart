package com.example.servicekartcustomer.dashboard.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.servicekartcustomer.R
import com.example.servicekartcustomer.dashboard.model.InstantData

class InstantAdapter(private val context: Context, private val nameList: ArrayList<InstantData>) : RecyclerView.Adapter<InstantAdapter.ViewHolder>() {
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
        RecyclerView.ViewHolder(inflater.inflate(R.layout.adapter_instant, parent, false)) {
        var image: ImageView = itemView.findViewById(R.id.ivInstantImage)
        var name: TextView = itemView.findViewById(R.id.tvInstantName)
        fun bind(context: Context, any: InstantData) {
            name.text = any.instantName
            Glide.with(context)
                .load(any.instantImage)
                .into(image)




        }


    }
}