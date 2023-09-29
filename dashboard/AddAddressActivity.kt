package com.example.servicekartcustomer.dashboard

import android.Manifest
import android.animation.ObjectAnimator
import android.content.Intent
import android.content.IntentSender
import android.content.pm.PackageManager
import android.location.Geocoder
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.RadioButton
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import com.example.servicekartcustomer.R
import com.example.servicekartcustomer.dashboard.model.AddAddressRequest
import com.example.servicekartcustomer.databinding.ActivityAddAddressBinding
import com.example.servicekartcustomer.extensions.SetGetData
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.GoogleMap.OnMarkerDragListener
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.google.android.gms.tasks.Task
import kotlinx.android.synthetic.main.activity_add_address.*
import java.util.*


class AddAddressActivity : FragmentActivity(), OnMapReadyCallback,
    GoogleMap.OnMarkerClickListener {
    private var mMap: GoogleMap? = null
    private lateinit var binding: ActivityAddAddressBinding
    private lateinit var addressViewModel: AddressViewModel
    var type = ""
    lateinit var userData: AddAddressRequest
    var name = ""
    var address = ""
    var flatNo = ""
    var landmark = ""
    var pincode = ""
    var phoneNumber = ""
    var addressType: Int = 0
    var radioButton: RadioButton? = null
    var latLng: LatLng? = null

    companion object {
        private val MY_PERMISSION_FINE_LOCATION = 101
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addressViewModel = ViewModelProvider(this).get(AddressViewModel::class.java)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_address)
        setUpAddAddressViewModel()
        binding.ivNavigation.setOnClickListener {
            checkPermission()
        }
        checkPermission()
        getAddressByGeoCodingLatLng(latitude = Double.MAX_VALUE, longitude = Double.MAX_VALUE)
//
//        binding.etAddress.addTextChangedListener(object : TextWatcher {
//            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
//
//            }
//
//            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
//
//            }
//
//            override fun afterTextChanged(s: Editable) {
//
//            }
//        })
        binding.btnSaveAddress.setOnClickListener {
            name = binding.etName.text.toString()

            address = binding.etAddress.text.toString()
            flatNo = binding.etFlatNo.text.toString()
            // mMap=supportFragmentManager.findFragmentById(R.id.fragmentMap)as SupportMapFragment
            landmark = binding.etLandmark.text.toString()
            pincode = binding.etPinCode.text.toString()
            phoneNumber = binding.etNumber.text.toString()
            addressType = binding.RadioGroupAddress.checkedRadioButtonId
            radioButton = findViewById(addressType)
            radioButton?.text
            if (checkValidation()) {

                userData = AddAddressRequest(
                    name,
                    address,
                    flatNo,
                    landmark,
                    pincode,
                    phoneNumber,
                    radioButton?.text.toString()
                )

                addressViewModel.addAddress(SetGetData.getUserId(this), userData)

            }
        }
        mMap?.setOnCameraIdleListener {
            binding.etAddress.animate().alpha(0f).duration = 500
            ObjectAnimator.ofFloat(binding.etAddress, "translationY", 0f).setDuration(250)
                .start()
            try {
                val nowLocation = mMap!!.cameraPosition.target
                getAddressByGeoCodingLatLng(nowLocation.latitude, nowLocation.longitude)
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }

    }



       /* mMap?.setOnCameraMoveStartedListener {
            binding..animate().alpha(1f).duration = 500
            ObjectAnimator.ofFloat(binding.ad, "translationY", -100f).setDuration(250).start()
        }
    }*/

    private fun checkPermission() {
        try {
            val permissionList = ArrayList<String>()
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                permissionList.add(Manifest.permission.ACCESS_FINE_LOCATION)
            }

            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                permissionList.add(Manifest.permission.ACCESS_COARSE_LOCATION)
            }

            if (permissionList.size > 0) {
                //  getString(R.string.required_location_permission)
                ActivityCompat.requestPermissions(this, permissionList.toTypedArray(), 100)
            } else {
                mMap?.isMyLocationEnabled = true
                mMap?.uiSettings?.isMyLocationButtonEnabled = false
                mMap?.uiSettings?.isCompassEnabled = true
                createLocationRequest()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }
          private fun createLocationRequest() {

            (supportFragmentManager.findFragmentById(R.id.fragmentMap) as SupportMapFragment).getMapAsync { tempGoogleMap ->
                mMap = tempGoogleMap
            }

            /*   val mapFragment = supportFragmentManager.findFragmentByI(R.id.fragmentMap) as SupportMapFragment
               mapFragment.getMapAsync(this)*/

            val locationRequest = LocationRequest.create().apply {
                interval = 5000
                fastestInterval = 5000
                priority = LocationRequest.PRIORITY_HIGH_ACCURACY
            }

            val builder = LocationSettingsRequest.Builder().addLocationRequest(locationRequest)
            val client: SettingsClient = LocationServices.getSettingsClient(this)
            val task: Task<LocationSettingsResponse> = client.checkLocationSettings(builder.build())

            task.addOnSuccessListener {
                try {
                    val fusedLocationProviderClient =
                        LocationServices.getFusedLocationProviderClient(this)
                    if (ActivityCompat.checkSelfPermission(
                            this,
                            Manifest.permission.ACCESS_FINE_LOCATION
                        ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                            this,
                            Manifest.permission.ACCESS_COARSE_LOCATION
                        ) != PackageManager.PERMISSION_GRANTED
                    ) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                        return@addOnSuccessListener
                    }
                    fusedLocationProviderClient.lastLocation.addOnSuccessListener { location ->
                        location?.let {
                            latLng = LatLng(it.latitude, it.longitude)
                            getAddressByGeoCodingLatLng(it.latitude,it.longitude)
                            mMap?.animateCamera(
                                CameraUpdateFactory.newCameraPosition(
                                    CameraPosition.fromLatLngZoom(
                                        LatLng(it.latitude, it.longitude),
                                        18F
                                    )
                                )
                            )
                        }



                        mMap?.addMarker(
                            MarkerOptions()
                                .title("Current Location")
                                .snippet("The most populous city in India.")
                                .position(latLng!!)
                        )
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }

            task.addOnFailureListener { exception ->
                if (exception is ResolvableApiException) {
                    try {
                        exception.startResolutionForResult(this, 3512)
                    } catch (sendEx: IntentSender.SendIntentException) {
                        sendEx.printStackTrace()
                    }
                }
            }
        }

        override fun onMarkerClick(p0: Marker): Boolean = false

        override fun onRequestPermissionsResult(
            requestCode: Int,
            permissions: Array<String>,
            grantResults: IntArray
        ) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
            when (requestCode) {
                MY_PERMISSION_FINE_LOCATION -> if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {//permission to access location grant
                    if (ActivityCompat.checkSelfPermission(
                            this,
                            Manifest.permission.ACCESS_FINE_LOCATION
                        ) == PackageManager.PERMISSION_GRANTED
                    ) {
                        mMap?.isMyLocationEnabled = true
                    }
                }
                //permission to access location denied
                else {
                    Toast.makeText(
                        applicationContext,
                        "This app requires location permissions to be granted",
                        Toast.LENGTH_LONG
                    ).show()
                    finish()
                }
            }
        }



    private fun getAddressByGeoCodingLatLng(latitude: Double, longitude: Double) {
        if (latitude != 0.0 && longitude != 0.0) {
            try {
                val addresses =
                    Geocoder(this, Locale.getDefault()).getFromLocation(latitude, longitude, 1)

                if (addresses.size > 0) {
                    val isFirstTime = null

                    var tempAddressText = ""
                    var sublocality = ""
                    addresses[0].subLocality?.let {
                        tempAddressText = "$it, "
                        sublocality = "$it "
                    }

                    addresses[0].subAdminArea?.let {
                        tempAddressText += "$it, "
                    }

                    addresses[0].locality?.let {
                        tempAddressText += "$it, "


                    }
                    etAddress.setText(addresses[0].locality)


//                    etFlatNo.setText(address[1].)
                    etPinCode.setText((addresses[0].postalCode))
                }

            } catch (e: Exception) {

            }
        }
    }
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

//         Add a marker in India and move the camera
//        val india = LatLng(20.5937, 78.9629)
//        mMap!!.addMarker(MarkerOptions().position(india).title("Marker in India"))
//        mMap!!.moveCamera(CameraUpdateFactory.newLatLng(india))

       if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            mMap!!.isMyLocationEnabled = true
        } else {//condition for Marshmello and above
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                    MY_PERMISSION_FINE_LOCATION
                )
            }
        }
        //move marker
        val india = LatLng(78.9629, 20.5937)
        mMap!!.setOnMarkerClickListener(this)
        mMap!!.moveCamera(CameraUpdateFactory.newLatLng(india))
        val markerOptions = MarkerOptions().position(india).title(
            "Current Location"
        ).draggable(true)
        mMap!!.addMarker(markerOptions)


        mMap!!.setOnMarkerDragListener(object : OnMarkerDragListener {
            override fun onMarkerDragStart(marker: Marker) {
                val position0: LatLng = marker.position

                Log.d(
                    javaClass.simpleName, String.format(
                        "Drag from %f:%f",
                        position0.latitude,
                        position0.longitude
                    )
                )
            }
            override fun onMarkerDrag(marker: Marker) {
                val position0: LatLng = marker.position

                Log.d(
                    javaClass.simpleName, String.format(
                        "Dragging to %f:%f", position0.latitude,
                        position0.longitude

                    )
                )
            }
            override fun onMarkerDragEnd(marker: Marker) {
               val  position0 = marker.position

                Log.d(javaClass.simpleName, String.format("Dragged to %f:%f",
                    position0.latitude,
                    position0.longitude))
            }
        })


    }

//    //Drag a marker
//    val perthLocation = LatLng(-31.90, 115.86)
//    val perth = mMap!!.addMarker(
//        MarkerOptions()
//            .position(perthLocation)
//            .draggable(true)
//    )

    //rotate a marker

//    val perthLocation = LatLng(-31.90, 115.86)
//    val perth = mMap.addMarker(
//        MarkerOptions()
//            .position(perthLocation)
//            .anchor(0.5f, 0.5f)
//            .rotation(90.0f)
//    )






    private fun setUpAddAddressViewModel() {
        addressViewModel.addAddressData.observe(this) {
            Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, MyAddress::class.java))
            finish()
        }
        addressViewModel.responseError.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }
    }

    private fun checkValidation(): Boolean {
        //permission to access location grant
        when {
            name.trim().isEmpty() -> {
                Toast.makeText(this, "PLease Enter Name", Toast.LENGTH_SHORT).show()
                return false
            }
            address.trim().isEmpty() -> {
                Toast.makeText(this, "PLease Enter Address", Toast.LENGTH_SHORT).show()
                return false
            }
            flatNo.trim().isEmpty() -> {
                Toast.makeText(this, "PLease Enter Flat Number", Toast.LENGTH_SHORT).show()
                return false
            }
            landmark.trim().isEmpty() -> {
                Toast.makeText(this, "PLease Enter Landmark", Toast.LENGTH_SHORT).show()
                return false
            }
            pincode.trim().isEmpty() -> {
                Toast.makeText(this, "PLease Enter PinCode", Toast.LENGTH_SHORT).show()
                return false
            }
            phoneNumber.trim().isEmpty() -> {
                Toast.makeText(this, "PLease Enter Phone Number", Toast.LENGTH_SHORT).show()
                return false
            }
            addressType == 0 -> {
                Toast.makeText(this, "PLease Select Address Type", Toast.LENGTH_SHORT).show()
                return false
            }
        }
        return true
    }
}


