package com.example.servicekartcustomer.dashboard

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.servicekartcustomer.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.firebase.database.*


class TrackOrderActivity : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var mMap: GoogleMap
    private var database: FirebaseDatabase = FirebaseDatabase.getInstance()
    private var dbReference: DatabaseReference = database.getReference("servicekart")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_track_order)
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.mMap) as SupportMapFragment
        mapFragment.getMapAsync(this)
        dbReference = database.reference
        dbReference.addValueEventListener(locListener)



    }

    private val locListener = object : ValueEventListener {
        //     @SuppressLint("LongLogTag")
        override fun onDataChange(snapshot: DataSnapshot) {
            if (snapshot.exists()) {
                //get the exact longitude and latitude from the database "ServiceKart"
                val location = snapshot.child("servicekart").getValue(TrackLocationInfo::class.java)
                val locationLat = location?.latitude
                val locationLong = location?.longitude
                //trigger reading of location from database using the button
                mMap.setOnClickListener {
                    if (locationLat != null && locationLong != null) {
                        // create a LatLng object from location
                        val latLng = LatLng(28.6300519, 77.3811512)
                        //create a marker at the read location and display it on the map
                        mMap.addMarker(
                            MarkerOptions().position(latLng)
                                .title("The user is currently here")
                        )
                        //specify how the map camera is updated
                        val update = CameraUpdateFactory.newLatLngZoom(latLng, 16.0f)
                        //update the camera with the CameraUpdate object
                        mMap.moveCamera(update)
                    } else {
                        // if location is null , log an error message
                        Log.e(TAG, "user location cannot be found")
                    }
                }


            }
        }

        override fun onCancelled(error: DatabaseError) {
            Toast.makeText(applicationContext, "Could not read from database", Toast.LENGTH_LONG)
                .show()
        }
    }


    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        // create a LatLng object from location
        val latLng = LatLng(29.6300522, 78.3811522)
        //create a marker at the read location and display it on the map
        mMap.addMarker(
            MarkerOptions().position(latLng)
                .title("The user is currently here")
        )
        //specify how the map camera is updated
        val update = CameraUpdateFactory.newLatLngZoom(latLng, 16.0f)
        //update the camera with the CameraUpdate object
        mMap.moveCamera(update)
        val zoom = CameraUpdateFactory.zoomTo(15f)
        mMap.animateCamera(zoom)
    }


    companion object {
        // TAG is passed into the Log.e methods used above to print information to the Logcat window
        private const val TAG = "TrackLocationActivity" // for debugging
    }

}
fun GoogleMap.setOnClickListener(function: () -> Any) {

}

