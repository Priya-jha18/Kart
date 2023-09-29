package com.example.servicekartcustomer.myOrders


import com.example.servicekartcustomer.extensions.ItemFragment
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.servicekartcustomer.R
import kotlinx.android.synthetic.main.item_recommended.view.*


class RecommendedAdapter(val context: ItemFragment, private val listRecom: ArrayList<RecommendedData>) :
    RecyclerView.Adapter<RecommendedAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater, parent)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(context, listRecom[position])

    }

    override fun getItemCount(): Int {
        return listRecom.size
    }

    class ViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.item_recommended, parent, false)) {


        fun bind(mContext: ItemFragment, RecommendedAdapter: RecommendedData) {
            Glide.with(mContext).load(RecommendedAdapter.StorePicture).into(itemView.ivXmas1)
            itemView.tvTiming.text=RecommendedAdapter.StoreName
            itemView.tvDistance1.text=RecommendedAdapter.Distance





        }
    }
}


