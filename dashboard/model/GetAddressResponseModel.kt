package com.example.servicekartcustomer.dashboard.model

import com.google.gson.annotations.SerializedName

data class GetAddressResponseModel(
    val status: Int,
    val message: String,
    val userAddress: List<UserAddress>
)

data class UserAddress(
    val name: String,
    val address: String,
    val flatNo: String,
    val pincode: String,
    val phoneNumber: String,
    val addressType: String,
    @SerializedName("_id")
    val id: String,
    @SerializedName("__v")
    val v: Long
)

