package com.example.servicekartcustomer.dashboard

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.servicekartcustomer.R
import com.example.servicekartcustomer.databinding.ActivityAddressActivtityBinding
import com.example.servicekartcustomer.extensions.SetGetData
import kotlinx.android.synthetic.main.activity_address_activtity.*

class MyAddress : AppCompatActivity() {
    /*var listAddress: ArrayList<UserAddress> = ArrayList()*/
    private lateinit var binding: ActivityAddressActivtityBinding
    private lateinit var getAddressViewModel: AddressViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_address_activtity)
        getAddressViewModel = ViewModelProvider(this).get(AddressViewModel::class.java)
        getAddressViewModel.getAddress(SetGetData.getUserId(this))
        recycleView.layoutManager = LinearLayoutManager(this)

        setUpGetAddressViewModel()
        initClicks()



    }

    /*override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return super.onCreateOptionsMenu(menu)
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.overflow_menu, menu)
        return true
    }*/

    private fun setUpGetAddressViewModel() {
        getAddressViewModel.getAddressData.observe(this) {

            recycleView.adapter = AddressAdapter(this, it.userAddress)

        }
        getAddressViewModel.responseError.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }
        getAddressViewModel.responseError.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }
    }



    private fun initClicks() {
        btnAddNewAddress.setOnClickListener {
            startActivity(
                Intent(this, AddAddressActivity::class.java)
            )
        }

    }
}

