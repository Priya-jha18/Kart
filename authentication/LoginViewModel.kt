package com.example.servicekartcustomer.authentication

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.servicekartcustomer.R
import com.example.servicekartcustomer.authentication.model.LogInResponseModel
import com.example.servicekartcustomer.networks.RetrofitUtil
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginViewModel : ViewModel() {


    var validationErr = MutableLiveData<Int>()
    var responseError = MutableLiveData<String>()
    var loginData = MutableLiveData<LogInResponseModel>()


    fun login(phoneNumber: String) {
        if (checkPhoneValidation(phoneNumber)) {
            val map = HashMap<String, String>()
            map["phone"] = phoneNumber

            val service = RetrofitUtil.getInstance()
            val call: Call<LogInResponseModel> = service.login(map)
            try {
                call.enqueue(object : Callback<LogInResponseModel> {
                    override fun onResponse(
                        call: Call<LogInResponseModel>,
                        response: Response<LogInResponseModel>
                    ) {

                        try {
                            val model: LogInResponseModel = response.body()!!
                            when (model.status) {
                                200 -> {
                                    loginData.value = model
                                }
                                201 -> {
                                    loginData.value = model
                                }

                                400 -> {
                                    responseError.value = model.message
                                }

                                else -> {
                                    responseError.value = model.message
                                }
                            }

                        }catch (e: Exception){
                            e.printStackTrace()
                            responseError.value = "Something went wrong!!"
                        }
                        //  val model2: SignupResponseModel = signup_response.body()!!
                    }

                    override fun onFailure(call: Call<LogInResponseModel>, t: Throwable) {
                        validationErr.value = R.string.error_generic
                    }
                })
            } catch (e: Exception) {
                e.printStackTrace()
                responseError.value = "Something went wrong!!"
            }
        }
    }

    private fun checkPhoneValidation(phone: String): Boolean {
        return when {
            phone.trim().isEmpty() -> {
                responseError.value = "Enter Phone Number"
                false
            }
            else -> true
        }
    }
}
