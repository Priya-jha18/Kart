package com.example.servicekartcustomer.dashboard.model

import com.google.gson.annotations.SerializedName

data class AddNewAddressResponseModel(

val status: Int,
val message: String,
val result: AddressListData
)

data class AddressListData(
    val name: String,
    val address: String,
    val flatNo: String,
    val pincode: String,
    val addressType: String,
    val phoneNumber: String,
    @SerializedName("_id")
    val id: String,
    @SerializedName("__v")
    val v: Long
)