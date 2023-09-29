package com.example.servicekartcustomer.extensions


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.servicekartcustomer.*
import com.example.servicekartcustomer.myOrders.OrderData
import com.example.servicekartcustomer.myOrders.OrderFragment
import kotlinx.android.synthetic.main.itemorder.view.*


class OrderAdapter(val context: OrderFragment, private val listOrder: ArrayList<OrderData>) :
    RecyclerView.Adapter<OrderAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater, parent)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(context, listOrder[position])

    }

    override fun getItemCount(): Int {
        return listOrder.size
    }

    class ViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.itemorder, parent, false)) {


        fun bind(mContext: OrderFragment, OrderAdapter: OrderData) {
            itemView.tvOrderId.text=OrderAdapter.orderId
            itemView.tvOrderDate.text=OrderAdapter.orderDate
            itemView.tvOpentime.text=OrderAdapter.OrderOpening
            itemView.tvItem.text=OrderAdapter.TotalItem
            itemView.tvPrice.text=OrderAdapter.Price
            itemView.btnTrackOrder.text=OrderAdapter.TrackOrder






        }
    }
}


