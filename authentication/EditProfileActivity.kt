package com.example.servicekartcustomer.authentication

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.provider.MediaStore.Images
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.servicekartcustomer.R
import com.example.servicekartcustomer.dashboard.DashBoardActivity
import com.example.servicekartcustomer.dashboard.DashBoardViewModel
import com.example.servicekartcustomer.databinding.ActivityEditProfileBinding
import com.example.servicekartcustomer.extensions.*
import kotlinx.android.synthetic.main.activity_edit_profile.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.ByteArrayOutputStream
import java.io.File
import java.util.*


class EditProfileActivity : AppCompatActivity() {
    private var filepath: Bitmap? = null
    private lateinit var getProfileViewModel: DashBoardViewModel
    private val idPermissions: Int = 101
    lateinit var datePicker: DatePickerDialog
    private lateinit var binding: ActivityEditProfileBinding
    private lateinit var signUpViewModel: SignUpViewModel
    private lateinit var updateViewModel: UpdateViewModel
    private var file: File? = null
    private val myCalendar: Calendar = Calendar.getInstance()
    var body: MultipartBody.Part? = null

    var type = ""
    var date: String = ""
    var gender: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_edit_profile)

        signUpViewModel = ViewModelProvider(this).get(SignUpViewModel::class.java)
        updateViewModel = ViewModelProvider(this).get(UpdateViewModel::class.java)
        getProfileViewModel = ViewModelProvider(this).get(DashBoardViewModel::class.java)

        if (intent.getStringExtra(IntentConstants.FROM_DASHBOARD) != null) {
            type = intent.getStringExtra(IntentConstants.FROM_DASHBOARD).toString()

        }

        if (type == "Profile") {
            getProfileViewModel.getProfile(SetGetData.getUserId(this))
        }

        setUpGetProfileViewModel()

        initClicks()
        phoneNumber()
        setUpSignUpViewModel()
        setUpUpdateViewModel()

        radioFemale.setOnClickListener {
            gender = "Female"
        }
        radioMale.setOnClickListener {
            gender = "Male"

        }
    }

    private fun setUpGetProfileViewModel() {
        getProfileViewModel.getProfileData.observe(this) {

            binding.etFirstName.setText(it.user.firstName)
            binding.etLastName.setText(it.user.lastName)
            binding.etPhoneNumber.setText(it.user.phone)
            binding.etEmail.setText(it.user.email)
            binding.etDob.setText(it.user.DOB)
            if (it.user.gender == "Male") {
                binding.radioMale.isChecked = true
            } else {
                binding.radioFemale.isChecked = true
            }
            Glide.with(this)
                .load(it.user.photo)
                .into(binding.ivUpload)

        }
        getProfileViewModel.responseError.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }
        getProfileViewModel.responseError.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun calender() {
        val date =
            OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                myCalendar[Calendar.YEAR] = year
                myCalendar[Calendar.MONTH] = monthOfYear
                myCalendar[Calendar.DAY_OF_MONTH] = dayOfMonth
                date = "$dayOfMonth-$monthOfYear-$year"
                etDob.setText("$dayOfMonth-$monthOfYear-$year")
            }

        etDob.setOnClickListener {
            val myCalendar: Calendar = Calendar.getInstance()
            datePicker = DatePickerDialog(
                this,
                R.style.DialogTheme,
                date,
                myCalendar.get(Calendar.YEAR),
                myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)
            )
            datePicker.show()
        }
    }

    //  function to check permission
    private fun checkAndRequestPermissions(context: Activity?): Boolean {
        val WExtstorePermission = ContextCompat.checkSelfPermission(
            context!!,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
        val cameraPermission = ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.CAMERA
        )
        val listPermissionsNeeded: MutableList<String> = ArrayList()
        if (cameraPermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.CAMERA)
        }
        if (WExtstorePermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded
                .add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(
                context, listPermissionsNeeded
                    .toTypedArray(),
                idPermissions
            )
            return false
        }
        return true
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            idPermissions -> if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.CAMERA
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                Toast.makeText(
                    applicationContext,
                    "FlagUp Requires Access to Camara.", Toast.LENGTH_SHORT
                )
                    .show()
            } else if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                Toast.makeText(
                    applicationContext,
                    "FlagUp Requires Access to Your Storage.",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                chooseImage(this)
            }
        }
    }

    // function to let's the user to choose image from camera or gallery
    private fun chooseImage(context: Context) {
        val optionsMenu = arrayOf<CharSequence>(
            "Take Photo",
            "Choose from Gallery",
            "Remove Photo"
        ) // create a menuOption Array
        // create a dialog for showing the optionsMenu
        val builder: AlertDialog.Builder = AlertDialog.Builder(context)
        // set the items in builder
        builder.setItems(optionsMenu,
            DialogInterface.OnClickListener { dialogInterface, i ->
                if (optionsMenu[i] == "Take Photo") {
                    // Open the camera and get the photo
                    val takePicture = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                    startActivityForResult(takePicture, 0)
                } else if (optionsMenu[i] == "Choose from Gallery") {
                    val pickPhoto =
                        Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                    startActivityForResult(pickPhoto, 1)
                } else if (optionsMenu[i] == "Remove Photo") {
                    ivUpload.setImageResource(R.drawable.upload)
                }
            })
        builder.show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        println("CameraStoragePermission.onActivityResult: ${data!!.data}")
        when (requestCode) {
            0 -> if (resultCode == RESULT_OK) {
                println("CameraStoragePermission.onActivityResult: ${data.extras?.get("data")}")
                filepath = data.extras?.get("data") as Bitmap
                Glide.with(applicationContext)
                    .load(data.extras?.get("data"))
                    .placeholder(R.drawable.upload)
                    .into(ivUpload)

            }
            1 -> if (resultCode == RESULT_OK) {
//                    file = File(getRealPathFromURI(data.data!!))

                // file = data.data
                Glide.with(applicationContext)
                    .load(data.data)
                    .placeholder(R.drawable.upload)
                    .into(ivUpload)
            }
        }

    }

    //PhoneNumber Validation
    private fun phoneNumber() {
        etPhoneNumber.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                p0: CharSequence?,
                p1: Int, p2: Int, p3: Int
            ) {
            }

            override fun onTextChanged(
                p0: CharSequence?,
                p1: Int, p2: Int, p3: Int
            ) {
                // check inputted characters is a valid phone number or not
                if (AppValidator.isValidMobile(p0.toString())) {
                    etPhoneNumber.error
                } else {
                    etPhoneNumber.error = "Invalid phone number."
                }
            }

            override fun afterTextChanged(p0: Editable?) {}

            // extension function to validate edit text inputted phone number
            fun CharSequence?.isValidPhoneNumber(): Boolean {
                return !isNullOrEmpty() && Patterns.PHONE.matcher(this).matches()
            }
        })
    }

    fun getImageUri(inContext: Context, inImage: Bitmap): Uri? {
        val bytes = ByteArrayOutputStream()
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
        val path = Images.Media.insertImage(inContext.contentResolver, inImage, "Title", null)
        return Uri.parse(path)
    }

    fun getRealPathFromURI(uri: Uri): String {
        var path = ""
        if (contentResolver != null) {
            val cursor: Cursor? = contentResolver.query(uri, null, null, null, null)
            if (cursor != null) {
                cursor.moveToFirst()
                val idx: Int = cursor.getColumnIndex(Images.ImageColumns.DATA)
                path = cursor.getString(idx)
                cursor.close()
            }
        }
        return path
    }

    private fun setUpSignUpViewModel() {
        signUpViewModel.SignupData.observe(this) {
            Toast.makeText(this, "Signing In", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, DashBoardActivity::class.java))
            SetGetData.setUserId(this, it.result.id)
            SetGetData.setPhone(this, it.result.phone)
            SetGetData.setEmail(this, it.result.email)
            SetGetData.setName(this, it.result.firstName)
            SetGetData.setProfilePic(this, it.result.photo)

        }
        signUpViewModel.validationErr.observe(this) {
            Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
        }
        signUpViewModel.responseError.observe(this) {
            Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()

        }
    }

    private fun setUpUpdateViewModel() {
        updateViewModel.UpdateData.observe(this) {
            Toast.makeText(this, "Your Profile has been Updated", Toast.LENGTH_SHORT).show()
            SetGetData.setName(this, it.updatedUser.firstName)
            SetGetData.setEmail(this, it.updatedUser.email)
            SetGetData.setProfilePic(this, it.updatedUser.photo)
            SetGetData.setPhone(this, it.updatedUser.phone)

        }
        updateViewModel.validationErr.observe(this) {
            Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
        }


        updateViewModel.responseError.observe(this) {
            Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()

        }
    }

    private fun initClicks() {

        tvSkip.setOnClickListener {
            startActivity(Intent(this, DashBoardActivity::class.java))
            finishAffinity()

        }

        etDob.setOnClickListener(View.OnClickListener {
            calender()
        })

        if (type == "Profile") {
            tvSkip.invisible()
            tvProfile.invisible()
        } else if (type == IntentConstants.FROM_SIGNUP)
            tvEdit.invisible()
        binding.arrowEditProfile.setOnClickListener {
            onBackPressed()
        }

        ivUpload.setOnClickListener {
            if (checkAndRequestPermissions(this)) {
                chooseImage(this)
            }
        }

        btnContinue.setOnClickListener {

            //Signup API
            if (type == IntentConstants.FROM_SIGNUP) {
                    if (filepath != null) {
                        file = File(getRealPathFromURI(getImageUri(this, filepath!!)!!))
                        val requestFile = RequestBody.create(
                            "multipart/form-data".toMediaTypeOrNull(),
                            file!!
                        )
                        // MultipartBody.Part is used to send also the actual file name
                        body = MultipartBody.Part.createFormData(
                            "productImage",
                            System.currentTimeMillis().toString() + "_media.jpg",
                            requestFile
                        )
                }

                //   body?.let { it1 ->
                parseDateToddMMyyyy(date)?.let { it2 ->
                    signUpViewModel.signup(
                        etFirstName.text.toString(),
                        etLastName.text.toString(),
                        gender,
                        etEmail.text.toString(),
                        it2,
                        etPhoneNumber.text.toString(),
                        body!!
                    )
                }
                //}
            } else {
                if (filepath != null) {
                    file = File(getRealPathFromURI(getImageUri(this, filepath!!)!!))
                    val requestFile = RequestBody.create(
                        "multipart/form-data".toMediaTypeOrNull(),
                        file!!
                    )
                    // MultipartBody.Part is used to send also the actual file name
                    body = MultipartBody.Part.createFormData(
                        "productImage",
                        System.currentTimeMillis().toString() + "_media.jpg",
                        requestFile
                    )
                } else {
                    val requestFile = RequestBody.create(
                        "text/plain".toMediaTypeOrNull(),
                        ""
                    )
                    // MultipartBody.Part is used to send also the actual file name
                    body = MultipartBody.Part.createFormData(
                        "productImage",
                        "",
                        requestFile
                    )
                }
                updateViewModel.update(
                    SetGetData.getUserId(this),
                    etFirstName.text.toString(),
                    etLastName.text.toString(),
                    gender,
                    etEmail.text.toString(),
                    etDob.text.toString(),
                    etPhoneNumber.text.toString(),
                    body!!
                )
            }
        }


/*
            fun onRadioButtonClicked(view: View?) {
                when {
                    radioMale. -> {
                        radioFemale.isChecked = false
                        gender = "Male"
                    }
                    radioFemale.isPressed -> {
                        radioMale.isChecked = false
                        gender = "Female"
                    }
                }
            }*/
    }
}





