package com.example.servicekartcustomer.dashboard

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.servicekartcustomer.R

class StoreItemAdapter(
    private val context: Context,
    private val nameList: ArrayList<StoreItemData>
) : RecyclerView.Adapter<StoreItemAdapter.ViewHolder>() {
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
        RecyclerView.ViewHolder(inflater.inflate(R.layout.adapter_store_item, parent, false)) {
        var storeimage: ImageView = itemView.findViewById(R.id.ivStoreItemPhoto)
        var storename: TextView = itemView.findViewById(R.id.tvStoreItemText)
        fun bind(context: Context, any: StoreItemData) {
            storename.text = any.storeItemName
            Glide.with(context)
                .load(any.storeItemImage)
                .into(storeimage)
        }
    }
}