package com.example.servicekartcustomer.dashboard


import com.example.servicekartcustomer.extensions.CategoriesFragment
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.servicekartcustomer.R
import kotlinx.android.synthetic.main.itemcategories.view.*


class CategoriesAdapter(val context: CategoriesFragment, private val listcat: ArrayList<CategoriesData>) :
    RecyclerView.Adapter<CategoriesAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater, parent)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(context, listcat[position])

    }

    override fun getItemCount(): Int {
        return listcat.size
    }

    class ViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.itemcategories, parent, false)) {


        fun bind(mContext: CategoriesFragment, categoriesAdapter: CategoriesData) {
            Glide.with(mContext).load(categoriesAdapter.picture).into(itemView.ivOrientMilk)
            Glide.with(mContext).load(categoriesAdapter.picture1).into(itemView.ivOrientMilk1)
            itemView.tvMilk.text=categoriesAdapter.name1
            itemView.tvMilk1.text=categoriesAdapter.name2





        }
    }
}


