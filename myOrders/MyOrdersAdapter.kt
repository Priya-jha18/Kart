package com.example.servicekartcustomer.myOrders

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.servicekartcustomer.R
import com.example.servicekartcustomer.dashboard.TrackOrderActivity
import java.util.ArrayList

class MyOrdersAdapter(private val context: Context, private val nameList: ArrayList<MyOrderData>) : RecyclerView.Adapter<MyOrdersAdapter.ViewHolder>() {
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
        RecyclerView.ViewHolder(inflater.inflate(R.layout.adapter_my_orders, parent, false)) {
        var myOPhoto: ImageView = itemView.findViewById(R.id.ivMyOrderDp)
        var myODate: TextView = itemView.findViewById((R.id.tvMyOrderDate))
        var myOName: TextView = itemView.findViewById(R.id.tvMyStoreName)
        var myOItems: TextView = itemView.findViewById(R.id.tvItemCount)
        var myOPrice: TextView = itemView.findViewById(R.id.tvPriceCount)
        var myOId: TextView = itemView.findViewById(R.id.tvMyOrderIdNumber)
        var myOOrderDetails: Button = itemView.findViewById(R.id.btnViewOrder)
        var myOTrackOrder: Button = itemView.findViewById(R.id.btnTrackOrder)

        fun bind(context: Context, any: MyOrderData) {
            myODate.text = any.myOrderDate
            myOName.text = any.myOrderName
            myOItems.text = any.myOrderItemCount
            myOPrice.text = any.myOrderPrice
            myOId.text = any.myOrderId
            Glide.with(context)
                .load(any.myOrderDp)
                .into(myOPhoto)
            myOOrderDetails.setOnClickListener{
                context.startActivity(Intent(itemView.context,OrderDetails2::class.java))
            }
            myOTrackOrder.setOnClickListener {
                context.startActivity(Intent(itemView.context, TrackOrderActivity::class.java))
            }



        }

    }
}
