package com.example.servicekartcustomer.dashboard


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.servicekartcustomer.*
import com.example.servicekartcustomer.dashboard.model.MonthData
import kotlinx.android.synthetic.main.itemmonth.view.*


class MonthAdapter(val context: MonthFragment, private val listMonth: ArrayList<MonthData>) :
    RecyclerView.Adapter<MonthAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater, parent)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(context, listMonth[position])

    }

    override fun getItemCount(): Int {
        return listMonth.size
    }

    class ViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.itemmonth, parent, false)) {


        fun bind(mContext: MonthFragment, MonthAdapter: MonthData) {
            itemView.tvOrderId1.text=MonthAdapter.orderId
            itemView.tvOrderDate1.text=MonthAdapter.orderDate
            itemView.tvItem1.text=MonthAdapter.TotalItem
            itemView.tvPrice2.text=MonthAdapter.Price1







        }
    }
}


