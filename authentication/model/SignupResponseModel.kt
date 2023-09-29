package com.example.servicekartcustomer.authentication.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class SignupResponseModel(
    val status: Int,
    val message: String,
    val result: Result
)

data class Result(
    val firstName: String,
    val lastName: String,
    val phone: String,
    val gender: String,
    val email: String,
    @SerializedName("DOB")
    val dob: String,
    val photo: String,
    @SerializedName("_id")
    val id: String,
    @SerializedName("v")
    val v: Int
)

