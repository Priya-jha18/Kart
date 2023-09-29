package com.example.servicekartcustomer.myOrders

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.servicekartcustomer.R
import kotlinx.android.synthetic.main.activity_order_details2.*
import kotlinx.android.synthetic.main.activity_summary.*
import kotlinx.android.synthetic.main.activity_summary.ivBackBlack

class OrderDetails2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_details2)

        ivBackBlack.setOnClickListener {
            onBackPressed()
        }
        btnRateOrder.setOnClickListener {
            startActivity(Intent(this,RateOrder::class.java))
        }
    }
}