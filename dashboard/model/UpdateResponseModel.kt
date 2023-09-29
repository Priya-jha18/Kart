package com.example.servicekartcustomer.dashboard.model

import com.google.gson.annotations.SerializedName

data class UpdateResponseModel(
    val status: Int,
    val message: String,
    val updatedUser: UpdatedUser
)

data class UpdatedUser(
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
    val __v: Int,
    val token: String
)
