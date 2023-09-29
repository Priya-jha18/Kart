package com.example.servicekartcustomer.dashboard


import com.example.servicekartcustomer.extensions.ItemFragment
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.servicekartcustomer.R
import kotlinx.android.synthetic.main.itemstore.view.*


class ItemStoreAdapter(val context: ItemFragment, private val listItem: ArrayList<ItemStoreData>) :
    RecyclerView.Adapter<ItemStoreAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater, parent)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(context, listItem[position])

    }

    override fun getItemCount(): Int {
        return listItem.size
    }

    class ViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.itemstore, parent, false)) {


        fun bind(mContext: ItemFragment, ItemStoreAdapter: ItemStoreData) {
            Glide.with(mContext).load(ItemStoreAdapter.image).into(itemView.iv_milk)
            itemView.tv_milk.text=ItemStoreAdapter.name
            itemView.tv_weight.text=ItemStoreAdapter.weight
            itemView.tv_price.text=ItemStoreAdapter.price
            itemView.btn_add1.text=ItemStoreAdapter.add1





        }
    }
}


