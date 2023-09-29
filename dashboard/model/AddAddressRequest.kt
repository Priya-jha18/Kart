package com.example.servicekartcustomer.dashboard.model

data class AddAddressRequest(
    val name: String,
    val address: String,
    val flatNo: String,
    val landmark: String,
    val pinCode: String,
    val phoneNumber: String,
    val addressType: String)
