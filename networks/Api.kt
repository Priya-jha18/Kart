package com.example.servicekartcustomer.networks

import HomeResponseModel
import com.example.servicekartcustomer.authentication.model.LogInResponseModel
import com.example.servicekartcustomer.authentication.model.SignupResponseModel
import com.example.servicekartcustomer.dashboard.model.*
import com.example.servicekartcustomer.dashboard.model.GetAddressResponseModel
import com.example.servicekartcustomer.dashboard.model.GetProfileResponseModel
import com.example.servicekartcustomer.dashboard.model.UpdateResponseModel
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*


interface
Api {

        @FormUrlEncoded
        @POST(WebConstants.LOG_IN)
        fun login(@FieldMap map: HashMap<String,String>): Call<LogInResponseModel>



        @Multipart
        @POST(WebConstants.SIGN_UP)
        fun signup(
                @Part("firstName") fullname:RequestBody,
                @Part("lastName") lastname:RequestBody,
                @Part("gender") gender:RequestBody,
                @Part("email") email:RequestBody,
                @Part("DOB") country_code:RequestBody,
                @Part("phone") mobile:RequestBody,
                @Part productImage: MultipartBody.Part
        ):  Call<SignupResponseModel>


        @FormUrlEncoded
        @POST(WebConstants.LOG_OUT)
        fun logout(@FieldMap map: HashMap<String,String>) :Call<LogOutResponseModel>


        @Multipart
        @PUT(WebConstants.UPDATE_PROFILE)
        fun update(
                @Part("id") _id:RequestBody,
                @Part("firstName") fullname:RequestBody,
                @Part("lastName") lastname:RequestBody,
                @Part("gender") gender:RequestBody,
                @Part("email") email:RequestBody,
                @Part("DOB") dateOfBirth:RequestBody,
                @Part("phone") mobile:RequestBody,
                @Part productImage: MultipartBody.Part
        ):  Call<UpdateResponseModel>

        @GET(WebConstants.GET_PROFILE)
        fun getProfile(@Query("id")id: String): Call<GetProfileResponseModel>

        @FormUrlEncoded
        @POST(WebConstants.ADD_NEW_ADDRESS)
        fun addAddress(@Body map: HashMap<String, Any>): Call<AddNewAddressResponseModel>

        @GET(WebConstants.GET_ADDRESS)
        fun getAddress(@Query("id")id: String): Call<GetAddressResponseModel>

        @GET(WebConstants.HOME)
        fun home(@Query("id")id: String):Call<HomeResponseModel>
}



