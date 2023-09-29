package com.example.servicekartcustomer.dashboard.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.servicekartcustomer.R
import com.example.servicekartcustomer.dashboard.model.FAQData

class FAQAdapter(private val context: Context, private val nameList: ArrayList<FAQData>) : RecyclerView.Adapter<FAQAdapter.ViewHolder>() {
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
        RecyclerView.ViewHolder(inflater.inflate(R.layout.adapter_faq, parent, false)) {
        var question: TextView = itemView.findViewById(R.id.tvFAQQuestion)
        var answer: TextView = itemView.findViewById(R.id.tvFAQAnswer)
        fun bind(context: Context, any: FAQData) {
            question.text = any.faqQuestion
            answer.text = any.faqAnswer

        }


    }
}
