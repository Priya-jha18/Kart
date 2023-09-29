package com.example.servicekartcustomer.dashboard


import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.servicekartcustomer.R

class CartAdapter(private val context: Context, private val nameList: ArrayList<CarttemData>) : RecyclerView.Adapter<CartAdapter.ViewHolder>() {
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
        RecyclerView.ViewHolder(inflater.inflate(R.layout.adapter_cart, parent, false)) {
        var orderPhoto: ImageView = itemView.findViewById(R.id.ivOrderPhoto)
        var orderName: TextView = itemView.findViewById(R.id.tvOrderName)
        var orderQuantity: TextView = itemView.findViewById(R.id.tvOrderQuantity)
        var orderPrice: TextView = itemView.findViewById(R.id.tvOrderAmount)
        fun bind(context: Context, any: CarttemData) {
            orderName.text = any.orderName
            orderQuantity.text = any.orderQuantity
            orderPrice.text = any.orderAmount
            Glide.with(context)
                .load(any.orderImage)
                .into(orderPhoto)




        }


    }
}