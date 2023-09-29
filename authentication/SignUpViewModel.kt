package com.example.servicekartcustomer.authentication

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.ExifInterface
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.servicekartcustomer.R
import com.example.servicekartcustomer.authentication.model.SignupResponseModel
import com.example.servicekartcustomer.networks.RetrofitUtil
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.io.FileOutputStream


class SignUpViewModel : ViewModel() {

    var validationErr = MutableLiveData<Int>()
    var SignupData = MutableLiveData<SignupResponseModel>()
    var responseError = MutableLiveData<String>()


    fun signup(
        firstName: String,
        lastName: String,
        gender: String,
        email: String,
        DOB: String,
        mobile: String,
        productImage: MultipartBody.Part
    ) {
        val Firstname = getRequestBody(firstName)
        val Lastname = getRequestBody(lastName)
        val Gender = getRequestBody(gender)
        val Email = getRequestBody(email)
        val DateOfBirth = getRequestBody(DOB)
        val Mobile = getRequestBody(mobile)
        val productImage = productImage //ph
        val service = RetrofitUtil.getInstance()
        val call: Call<SignupResponseModel> = service.signup(
            Firstname,
            Lastname,
            Gender,
            Email,
            DateOfBirth,
            Mobile,
            productImage
        )
        call.enqueue(object : Callback<SignupResponseModel> {
            override fun onFailure(call: Call<SignupResponseModel>, t: Throwable) {

                validationErr.value = R.string.error_generic


            }

            override fun onResponse(
                call: Call<SignupResponseModel>,
                response: Response<SignupResponseModel>
            ) {
                response.body()?.let { model ->
                {   when (model.status) {
                        200 -> SignupData.value = model

                        400 -> {
                            responseError.value = model.message
                        }
                        else -> {
                            responseError.value = model.message

                        }
                    }
                }
                }
            }

        })

    }

    private fun getRequestBody(input: String): RequestBody {
        return RequestBody.create("text/plain".toMediaTypeOrNull(), input)
    }

    private fun getAttachmentFilePart(file: File, name: String): MultipartBody.Part {
        val oldExif = ExifInterface(file.path)
        val o: String = oldExif.getAttribute(ExifInterface.TAG_ORIENTATION)!!
        try {
            val compressionRatio = 25
            val bitmap = BitmapFactory.decodeFile(file.path)
            bitmap.compress(
                Bitmap.CompressFormat.JPEG,
                compressionRatio,
                FileOutputStream(file)
            )
            val newExif = ExifInterface(file.path)
            newExif.setAttribute(ExifInterface.TAG_ORIENTATION, o)
            newExif.saveAttributes()
        } catch (t: Throwable) {
            t.printStackTrace()
        }
        val filePart = MultipartBody.Part.createFormData(
            name,
            file.name,
            RequestBody.create("image/*".toMediaTypeOrNull(), file)
        )
        return filePart
    }

}










