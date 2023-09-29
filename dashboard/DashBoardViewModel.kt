package com.example.servicekartcustomer.dashboard

import HomeResponseModel
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.servicekartcustomer.dashboard.model.GetProfileResponseModel
import com.example.servicekartcustomer.dashboard.model.LogOutResponseModel
import com.example.servicekartcustomer.extensions.SharedPrefManager
import com.example.servicekartcustomer.networks.RetrofitUtil
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DashBoardViewModel : ViewModel() {

    var logoutData = MutableLiveData<LogOutResponseModel>()
    var getHomeData = MutableLiveData<HomeResponseModel>()
    var getProfileData = MutableLiveData<GetProfileResponseModel>()
    var responseError = MutableLiveData<String>()

    fun getProfile(id: String) {
        val service = RetrofitUtil.getInstance()
        val call: Call<GetProfileResponseModel> = service.getProfile(id)

        /*    GetProfileResponseModel = ViewModelProvider(this).get(DashBoardViewModel::class.java)*/
        call.enqueue(object : Callback<GetProfileResponseModel> {
            override fun onFailure(call: Call<GetProfileResponseModel>, t: Throwable) {
                responseError.value = "Something went wrong!!"
            }

            override fun onResponse(
                call: Call<GetProfileResponseModel>,
                response: Response<GetProfileResponseModel>
            ) {
                response.body()?.let { model ->
                    when (model.status) {
                        200 -> {
                            getProfileData.value = model
                        }
                        400 -> {
                            responseError.value = model.message

                        }
                        else -> {
                            responseError.value = model.message
                        }
                    }
                } ?: run {
                    responseError.value = "Something went wrong"
                }
            }
        })

    }

    fun logout(id: String) {
        val map = HashMap<String, String>()
        map["id"] = id

        val service = RetrofitUtil.getInstance()
        val call: Call<LogOutResponseModel> = service.logout(map)
        call.enqueue(object : Callback<LogOutResponseModel> {
            override fun onFailure(call: Call<LogOutResponseModel>, t: Throwable) {
                Log.d("######", "wrong")
            }

            override fun onResponse(
                call: Call<LogOutResponseModel>,
                response: Response<LogOutResponseModel>
            ) {
//                if (response.isSuccessful){}
                response.body()?.let { model ->
                    when (model.status) {
                        200 -> {
                            logoutData.value = model
                        }
                        500 -> {
                            responseError.value = model.message
                        }

                        else -> {
                            responseError.value = model.message
                        }
                    }
                } ?: run {
                    SharedPrefManager.clear()
                    responseError.value = "Something went wrong"
                }
            }
        })
    }
    fun Home(id: String) {
        val service = RetrofitUtil.getInstance()
        val call: Call<HomeResponseModel> = service.home(id)

        call.enqueue(object : Callback<HomeResponseModel> {
            override fun onFailure(call: Call<HomeResponseModel>, t: Throwable) {
                responseError.value = "Something went wrong!!"
            }

            override fun onResponse(
                call: Call<HomeResponseModel>,
                response: Response<HomeResponseModel>
            ) {
                response.body()?.let { model ->
                    when (model.status) {
                        200 -> {
                            getHomeData.value = model
                        }
                        404 -> {
                            responseError.value = model.message

                        }
                        else -> {
                            responseError.value = model.message
                        }
                    }
                } ?: run {
                    responseError.value = "Something went wrong"
                }
            }
        })

    }


}



