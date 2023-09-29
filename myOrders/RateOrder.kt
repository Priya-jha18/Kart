package com.example.servicekartcustomer.myOrders

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.servicekartcustomer.R
import com.example.servicekartcustomer.dashboard.adapter.DailyAdapter
import com.example.servicekartcustomer.dashboard.model.OfferData
import kotlinx.android.synthetic.main.activity_rate_order.*
import kotlinx.android.synthetic.main.fragment_home.*


class RateOrder : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rate_order)

        setAdapter()

        rating()

        ivBackBlack.setOnClickListener {
            onBackPressed()
        }
    }

    private fun setAdapter() {
        val rateList: ArrayList<RateData> = ArrayList()

        rvRateOrders.layoutManager = LinearLayoutManager(this)
        rateList.add(
            RateData(
                "#12345",
                "27 July, 2022",
                "02",
                "₹10.02",
                "https://cdn-icons-png.flaticon.com/512/522/522015.png",
                "EGOClash"
            )
        )
        rateList.add(
            RateData(
                "#12345",
                "27 July, 2022",
                "02",
                "₹10.02",
                "https://cdn-icons-png.flaticon.com/512/522/522015.png",
                "EGOClash"
            )
        )

        rvRateOrders.adapter = RateAdapter(this, rateList)
    }


    private fun rating() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            try {
                val progressDrawable: Drawable = ratingBar.progressDrawable
                DrawableCompat.setTint(
                    progressDrawable,
                    ContextCompat.getColor(this, R.color.rating_progress)
                )
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}