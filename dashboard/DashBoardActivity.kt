package com.example.servicekartcustomer.dashboard

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.location.Geocoder
import android.location.LocationManager
import android.os.Bundle
import android.view.WindowManager
import android.widget.ImageView
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.servicekartcustomer.BuildConfig
import com.example.servicekartcustomer.R
import com.example.servicekartcustomer.authentication.TermsAndConditionFragment
import com.example.servicekartcustomer.databinding.ActivityMainBinding
import com.example.servicekartcustomer.extensions.*
import com.example.servicekartcustomer.myOrders.MyOrders
import com.facebook.FacebookSdk
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.ivProfilePicture
import java.util.*
import com.karumi.dexter.listener.PermissionRequest
import android.location.LocationListener
import android.location.Location
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.servicekartcustomer.authentication.LoginActivity
import com.google.android.gms.maps.model.LatLng



class DashBoardActivity : BaseActivity() {
    private lateinit var logoutViewModel: DashBoardViewModel

    // private latent var getHomeViewModel: DashBoardViewModel
    private lateinit var binding: ActivityMainBinding
    private lateinit var textName: TextView
    private lateinit var profilePicture: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /* getHomeViewModel = ViewModelProvider(this).get(DashBoardViewModel::class.java)
        getHomeViewModel.Home(SetGetData.getUserId(this)) */
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        checkPermission()
        requestLocationPermission()
        logoutViewModel = ViewModelProvider(this).get(DashBoardViewModel::class.java)

        val header = nvSideNavigation.getHeaderView(0)
        textName = header.findViewById(R.id.tvSideName) as TextView
        profilePicture = header.findViewById(R.id.ivProfilePicture) as ImageView

        keyboardFocus()

        navigationDesign()

        replaceFragment(HomeFragment())

        sideNavigation()

        onClicks()

        version()

           logoutSetupModel()

        showData()
        // dashboardSetUpModel()
    }


//            binding.etFirstName.setText(it.user.firstName)
//            binding.etLastName.setText(it.user.lastName)
//            binding.etPhoneNumber.setText(it.user.phone)
//            binding.etEmail.setText(it.user.email)
//            binding.etDob.setText(it.user.DOB)
//            if (it.user.gender == "Male") {
//                binding.radioMale.isChecked = true
//            } else {
//                binding.radioFemale.isChecked = true
//            }
//
   //         Glide.with(this)
  //   .load(it.user.photo)
      //          .into(binding.ivUpload)



    /* getHomeViewModel.responseError.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }
        getHomeViewModel.responseError.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }
    }*/

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount == R.id.itemHome)
            super.onBackPressed()
            finishAffinity()
        if(supportFragmentManager.backStackEntryCount != R.id.itemHome)
            home()
            replaceFragment(HomeFragment())

    }



    override fun onResume() {
        super.onResume()
        SetGetData.getName(this)
        SetGetData.getProfilePic(this)
    }

    private fun showData() {
        Glide.with(this)
            .load(SetGetData.getProfilePic(this))
            .into(binding.ivProfilePicture)
        Glide.with(this)
            .load(SetGetData.getProfilePic(this))
            .into(profilePicture)
        binding.tvName.text = SetGetData.getName(this)
        textName.text = SetGetData.getName(this)

    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.MainFrame, fragment)
            .commit()
    }

    /*  private fun replaceFragment(fragment: Fragment) {
          val backStateName = fragment.javaClass.name
          val manager: FragmentManager = this.supportFragmentManager
          val fragmentPopped = manager.popBackStackImmediate(backStateName, 0)
          if (!fragmentPopped) { //fragment not in back stack, create it.
              val ft: FragmentTransaction = manager.beginTransaction()
              ft.replace(R.id.MainFrame, fragment, fragment.javaClass.name)
              ft.addToBackStack(null)
              ft.commit()
          }*/


    private fun sideNavigation() {
        binding.nvSideNavigation.setNavigationItemSelectedListener { item ->
            when (item.itemId) {

                R.id.itemHome -> {
                    binding.sideDrawer.closeDrawer(GravityCompat.START)
                    home()
                    replaceFragment(HomeFragment())
                }
                R.id.itemCart -> {
                    sideDrawer.closeDrawer(GravityCompat.START)
                    notHome()
                    tvToolBarName.text = item.title
                    replaceFragment(CartFragment())
                }
                R.id.itemFAQ -> {
                    sideDrawer.closeDrawer(GravityCompat.START)
                    replaceFragment(FAQFragment())
                    tvToolBarName.text = item.title
                    notHome()
                }
                R.id.itemOrders -> {
                    sideDrawer.closeDrawer(GravityCompat.START)
                    replaceFragment(MyOrders())
                    tvToolBarName.text = item.title
                    notHome()
                }
                R.id.itemAccount -> {
                    sideDrawer.closeDrawer(GravityCompat.START)
                    replaceFragment(MyAccountFragment())
                    tvToolBarName.text = item.title
                    notHome()
                }
                R.id.itemConditions -> {
                    sideDrawer.closeDrawer(GravityCompat.START)
                    replaceFragment(TermsAndConditionFragment())
                    tvToolBarName.text = item.title
                    notHome()
                }
                R.id.itemLogout -> {
//                    binding.pBar.visible()
                    sideDrawer.closeDrawer(GravityCompat.START)
                    logoutViewModel.logout(SetGetData.getUserId(this))
                    SharedPrefManager.clear()
                }
            }
            true
        }
//        pBar.invisible()

    }

    private fun navigationDesign() {
        sideDrawer.useCustomBehavior(GravityCompat.START)
        SetGetData.getProfilePic(this)
        SetGetData.getName(this)

        sideDrawer.setViewScale(
            GravityCompat.START,
            0.9f
        ) //set height scale for main view (0f to 1f)
        sideDrawer.setViewElevation(
            GravityCompat.START,
            20f
        ) //set main view elevation when drawer open (dimension)
        sideDrawer.setViewScrimColor(
            GravityCompat.START,
            Color.TRANSPARENT
        ) //set drawer overlay color (color)
        sideDrawer.setDrawerElevation(GravityCompat.START, 20f) //set drawer elevation (dimension)
        sideDrawer.setContrastThreshold(3f) //set maximum of contrast ratio between white text and background color.
        sideDrawer.setRadius(GravityCompat.START, 25f)

    }

    private fun onClicks() {
        clRightTop
            .setOnClickListener {
                sideDrawer.openDrawer(GravityCompat.START)
            }
    }

    private fun keyboardFocus() {// soft keyboard
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
    }

    private fun notHome() {
        tvToolBarName.visible()
        tvHello.gone()
        tvName.gone()
        ivProfilePicture.gone()
        ivHamburger.visible()
        ivHamburger.setImageResource(R.drawable.ic_hamburger_black)
        tvAddress.gone()
        ivDropDown.gone()
        ivNotificationMain.gone()
        clRightTop.setBackgroundColor(ContextCompat.getColor(applicationContext, R.color.white))
    }

    private fun home() {
        tvToolBarName.gone()
        tvName.visible()
        tvName.text = (getString(R.string.userName))
        tvHello.visible()
        tvHello.text = (getString(R.string.hello))
        tvAddress.visible()
        clRightTop.visible()
        tvAddress.text = (getString(R.string.address))
        ivDropDown.visible()
        ivProfilePicture.visible()
        ivHamburger.visible()
        ivHamburger.setImageResource(R.drawable.ic_hamburger)
        clRightTop.setBackgroundResource((R.drawable.blue_top))
        ivNotificationMain.visible()

    }

    private fun version() {
        val versionCode: Int = BuildConfig.VERSION_CODE
        val versionName: String = BuildConfig.VERSION_NAME
        binding.tvVersionCode.text = versionCode.toString()
        binding.tvVersionName.text = versionName
    }

    private fun logoutSetupModel() {
        val startActivity = Intent(this, LoginActivity::class.java)
        logoutViewModel.logoutData.observe(this) {
            Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
            startActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(startActivity)
        }
        logoutViewModel.responseError.observe(this) {
            startActivity(Intent(this, LoginActivity::class.java))
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }
    }
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
                getAddressByGeoCodingLatLng(latitude = Double.MAX_VALUE, longitude = Double.MAX_VALUE)
                ActivityCompat.requestPermissions(this, permissionList.toTypedArray(), 100)
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }

    }
    private fun requestLocationPermission() {
        Dexter.withActivity(this).withPermissions(
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION
        ).withListener(object :
            MultiplePermissionsListener {
            override fun onPermissionsChecked(report: MultiplePermissionsReport) {
                if (report.areAllPermissionsGranted()) {
                    showLocationPrompt()
                }

                if (report.isAnyPermissionPermanentlyDenied) {
                    //showPermissionDeniedDialog()
                }
            }

            override fun onPermissionRationaleShouldBeShown(
                permissions: List<PermissionRequest>,
                token: PermissionToken
            ) {
                token.continuePermissionRequest()
            }
        }).withErrorListener {
           // getString(R.string.errorOccurred).toToast()
        }
    }
    private fun showLocationPrompt() {
        try {
            val locationManager = FacebookSdk.getApplicationContext()
                .getSystemService(LOCATION_SERVICE) as LocationManager
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5000, 5F, object :
                LocationListener {
                override fun onLocationChanged(location: Location) {
                    getAddressByGeoCodingLatLng(location.latitude, location.longitude)
                    // googleMap?.animateCamera(
                    CameraUpdateFactory.newCameraPosition(
                        CameraPosition.fromLatLngZoom(
                            LatLng(location.latitude, location.longitude),
                            18F
                        )
                    )
                    locationManager.removeUpdates(this)
                }


                override fun onProviderDisabled(provider: String) {
                  /*  LocationServices.SettingsApi.checkLocationSettings(
                        googleLocationClient,
                        locationRequestCommon
                    ).setResultCallback { result ->
                        val status = result.status
                        when (status.statusCode) {
                            LocationSettingsStatusCodes.SUCCESS -> {

                            }
                            LocationSettingsStatusCodes.RESOLUTION_REQUIRED -> try {
                                status.startResolutionForResult(this@DashBoardActivity, 1000)
                            } catch (e: IntentSender.SendIntentException) {
                                e.printStackTrace()
                            }
                            LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE -> {

                            }
                        }*/

                }



                override fun onProviderEnabled(provider: String) {
                }
            })
        } catch (e: SecurityException) {
            e.printStackTrace()
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
                    Log.d("Address check","Test"+addresses[0].locality)
                    tvAddress.text = addresses[0].locality


//                    etFlatNo.setText(address[1].)

                }

            } catch (e: Exception) {

            }
        }
    }


}

