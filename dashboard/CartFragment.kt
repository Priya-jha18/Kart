package com.example.servicekartcustomer.dashboard

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.servicekartcustomer.R
import kotlinx.android.synthetic.main.fragment_cart.*


class CartFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_cart, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter()
        btnCheckOut.setOnClickListener {
            startActivity(Intent(context, SummaryActivity::class.java)
            ) }
    }

    private fun setAdapter(){

        rvCart.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        var carttemList: ArrayList<CarttemData> = ArrayList()

        carttemList.add(
            CarttemData("https://cdn-icons-png.flaticon.com/512/1139/1139982.png","₹20","Milk","500 Ml")
        )
        carttemList.add(
            CarttemData("https://cdn-icons-png.flaticon.com/512/2052/2052358.png","₹20","Milk","500 Ml")
        )
        carttemList.add(
            CarttemData("https://cdn-icons-png.flaticon.com/512/1139/1139982.png","₹20","Milk","500 Ml")
        )
        carttemList.add(
            CarttemData("https://cdn-icons-png.flaticon.com/512/2052/2052358.png","₹20","Milk","500 Ml")
        )
        rvCart.adapter = CartAdapter(requireContext(), carttemList)

    }
    }



