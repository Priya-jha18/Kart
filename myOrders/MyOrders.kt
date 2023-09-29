package com.example.servicekartcustomer.myOrders

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.servicekartcustomer.R
import kotlinx.android.synthetic.main.fragment_my_orders.*

class MyOrders : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_my_orders, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setAdapter()
    }

    private fun setAdapter() {

        rvMyOrders.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        var myOrderList: ArrayList<MyOrderData> = ArrayList()

        myOrderList.add(
            MyOrderData(
                "27 Jan 2022",
                "#23422",
                "https://cdn-icons-png.flaticon.com/512/2052/2052358.png",
                "Mother Dairy",
                "2",
                "₹ 200.00"
            )
        )
        myOrderList.add(
            MyOrderData(
                "28 Jan 2022",
                "#23422",
                "https://cdn-icons-png.flaticon.com/512/1139/1139982.png",
                "27/7 Store",
                "8",
                "₹ 9200.00"
            )
        )
        myOrderList.add(
            MyOrderData(
                "27 Jan 2022",
                "#23422",
                "https://cdn-icons-png.flaticon.com/512/2052/2052358.png",
                "Mother Dairy",
                "2",
                "₹ 200.00"
            )
        )

        rvMyOrders.adapter = MyOrdersAdapter(requireContext(), myOrderList)
    }

}