package com.example.servicekartcustomer.myOrders

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.example.servicekartcustomer.R

class Search : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
    var DetailList: ArrayList<SearchData> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
    }
}