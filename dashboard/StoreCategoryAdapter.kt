package com.example.servicekartcustomer.dashboard

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.servicekartcustomer.R
import com.example.servicekartcustomer.extensions.StoreDetailsActivity

class StoreCategoryAdapter(
    private val context: Context,
    private val nameList: ArrayList<StoreCategoryData>
) : RecyclerView.Adapter<StoreCategoryAdapter.ViewHolder>() {
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
        RecyclerView.ViewHolder(inflater.inflate(R.layout.adapter_store_category, parent, false)) {
        var categoryImage: ImageView = itemView.findViewById(R.id.ivSCServiceProviderProfile)
        var categoryName: TextView = itemView.findViewById(R.id.tvSCStoreName)
        var categoryProvider: TextView = itemView.findViewById(R.id.tvSCProvider)
        var categoryTime: TextView = itemView.findViewById(R.id.tvSCTime)
        var categoryDistance: TextView = itemView.findViewById(R.id.tvSCDistance)
        var itemClick: ConstraintLayout = itemView.findViewById(R.id.clStoreItem)
        fun bind(context: Context, any: StoreCategoryData) {
            categoryName.text = any.storeCategoryName
            categoryProvider.text = any.storeCategoryProvider
            categoryTime.text = any.storeCategoryTime
            categoryDistance.text = any.storeCategoryDistance
            Glide.with(context)
                .load(any.storeCategoryImage)
                .into(categoryImage)
            itemClick.setOnClickListener {
                context.startActivity(Intent(context, StoreDetailsActivity::class.java))
            }


        }


    }
}