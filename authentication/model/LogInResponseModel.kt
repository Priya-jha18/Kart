package com.example.servicekartcustomer.authentication.model

import com.google.gson.annotations.SerializedName

data class  LogInResponseModel(
    val status: Int,
    val message: String,
    val user: User
)

data class User(
    @SerializedName("_id")
    val id: String,
    val firstName: String,
    val lastName: String,
    val phone: String,
    val gender: String,
    val email: String,
    @SerializedName("DOB")
    val dob: String,
    val photo: String,
    val token: String,
    @SerializedName("__v")
    val v: Long
)


