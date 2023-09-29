package com.example.servicekartcustomer.dashboard.model

import com.google.gson.annotations.SerializedName

data class GetProfileResponseModel(
    val status: Int,
    val user: User,
    val message: String,
    val token: String)

data class User (
    @SerializedName("_id")
    val _id: String,
    val firstName: String,
    val lastName: String,
    val phone: String,
    val gender: String,
    val email: String,
    val DOB: String,
    val photo: String,
    val order: List<Any>,
    val cart: List<Any>,
    val address: List<Any>,
    @SerializedName("__v")
    val __v: Long
)
