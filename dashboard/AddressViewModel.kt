package com.example.servicekartcustomer.dashboard

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.servicekartcustomer.dashboard.model.AddAddressRequest
import com.example.servicekartcustomer.dashboard.model.AddNewAddressResponseModel
import com.example.servicekartcustomer.dashboard.model.GetAddressResponseModel
import com.example.servicekartcustomer.networks.RetrofitUtil
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddressViewModel: ViewModel() {

    var addAddressData = MutableLiveData<AddNewAddressResponseModel>()
    var getAddressData = MutableLiveData<GetAddressResponseModel>()
    var responseError = MutableLiveData<String>()

    fun addAddress(id: String, addAddressRequest: AddAddressRequest) {
        val map = HashMap<String, Any>()
        map ["id"] = id
        map["name"] = addAddressRequest.name
        map["address"] = addAddressRequest.address
        map["flatNo"] = addAddressRequest.flatNo
        map["landmark"] = addAddressRequest.landmark
        map["pincode"] = addAddressRequest.pinCode
        map["addressType"] = addAddressRequest.addressType
        map["phoneNumber"] = addAddressRequest.phoneNumber

        val service = RetrofitUtil.getInstance()
        val call: Call<AddNewAddressResponseModel> = service.addAddress(map)

        call.enqueue(object : Callback<AddNewAddressResponseModel> {

            override fun onFailure(call: Call<AddNewAddressResponseModel>, t: Throwable) {
            }

            override fun onResponse(
                call: Call<AddNewAddressResponseModel>,
                response: Response<AddNewAddressResponseModel>
            ) {
                response.body()?.let { model ->
                    when (model.status) {
                        200 -> {
                            addAddressData.value = model
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
    fun getAddress(id: String) {
        val service = RetrofitUtil.getInstance()
        val call: Call<GetAddressResponseModel> = service.getAddress(id)

        /*    GetProfileResponseModel = ViewModelProvider(this).get(DashBoardViewModel::class.java)*/
        call.enqueue(object : Callback<GetAddressResponseModel> {
            override fun onFailure(call: Call<GetAddressResponseModel>, t: Throwable) {
                responseError.value = "Something went wrong!!"
            }

            override fun onResponse(
                call: Call<GetAddressResponseModel>,
                response: Response<GetAddressResponseModel>
            ) {
                response.body()?.let { model ->
                    when (model.status) {
                        200 -> {
                            getAddressData.value = model
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

    /*fun getAddress(id: String) {
       val service = RetrofitUtil.getInstance()
       val call: Call<GetAddressResponseModel> = service.getAddress(id)

       call.enqueue(object : Callback<GetAddressResponseModel> {
           override fun onFailure(call: Call<GetAddressResponseModel>, t: Throwable) {
           }
           override fun onResponse(
               call: Call<GetAddressResponseModel>,
               response: Response<GetAddressResponseModel>
           ) {
               response.body()?.let { model ->
                   when (model.status) {
                       200 -> {
                           getAddressData.value = model
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
   }*/
}