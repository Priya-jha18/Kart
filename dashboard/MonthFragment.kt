package com.example.servicekartcustomer.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.servicekartcustomer.R
import com.example.servicekartcustomer.dashboard.model.MonthData
import kotlinx.android.synthetic.main.fragment_month.*


class MonthFragment : Fragment() {

    var listMonth: ArrayList<MonthData> = ArrayList()
    // Required empty public constructor


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_month, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listMonth.add(MonthData("Order ID : #4687","17 Feb 2021","Total Items : 02","56.00"))
                recycleView1.layoutManager = LinearLayoutManager(requireContext())
                recycleView1.adapter = MonthAdapter(this, listMonth)
    }
}



