package com.example.servicekartcustomer.dashboard

import android.app.PendingIntent.getActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.servicekartcustomer.R
import kotlinx.android.synthetic.main.activity_add_new_card.*


class AddNewCard : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_card)

        rgCardSelection.setOnCheckedChangeListener { group, checkedId -> // find which radio button is selected
            when (checkedId == R.id.radioDebit) {


            }
        }
    }
}