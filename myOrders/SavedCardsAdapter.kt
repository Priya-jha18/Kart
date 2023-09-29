package com.example.servicekartcustomer.myOrders

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.servicekartcustomer.R


class SavedCardsAdapter(private val context: Context, private val nameList: ArrayList<CardData>) :
    RecyclerView.Adapter<SavedCardsAdapter.ViewHolder>() {
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
        RecyclerView.ViewHolder(inflater.inflate(R.layout.adapter_saved_cards, parent, false)) {
        var cardNumber: TextView = itemView.findViewById(R.id.tvCardNumber)
        var cardName: TextView = itemView.findViewById(R.id.tvCardName)
        var cardExpireMonth: TextView = itemView.findViewById(R.id.tvMonth)
        var cardExpireYear: TextView = itemView.findViewById(R.id.tvYear)


        fun bind(context: Context, any: CardData) {
            cardName.text = any.cardHolderName
            cardNumber.text = any.cardNumber
            cardExpireMonth.text = any.cardValidMonth
            cardExpireYear.text = any.cardValidYear
        }

    }
}
