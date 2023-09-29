package com.example.servicekartcustomer.dashboard

import android.os.Bundle
import com.example.servicekartcustomer.extensions.BaseActivity
import com.example.servicekartcustomer.R
import kotlinx.android.synthetic.main.activity_summary.*

class SummaryActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_summary)

        ivBackBlack.setOnClickListener {
            onBackPressed()
        }
    }


}