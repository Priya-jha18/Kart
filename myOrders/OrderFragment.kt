package com.example.servicekartcustomer.myOrders

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.servicekartcustomer.R
import com.example.servicekartcustomer.extensions.OrderAdapter
import kotlinx.android.synthetic.main.fragment_order.*


class OrderFragment : Fragment() {

    var listOrder: ArrayList<OrderData> = ArrayList()
    // Required empty public constructor


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_order, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        listOrder.add(
            OrderData(
                "Order ID : #4687",
                "17 Feb 2021",
                "24/7 Store",
                "Total Items : 02,",
                "Price:",
                "TrackOrder"
            )
        )
        listOrder.add(
            OrderData(
                "Order ID : #4687",
                "17 Feb 2021",
                "24/7 Store",
                "Total Items : 02,",
                "Price:",
                "TrackOrder"
            )
        )
        recycle1.layoutManager = LinearLayoutManager(requireContext())
        recycle1.adapter = OrderAdapter(this, listOrder)


    }
}





