package com.example.servicekartcustomer.authentication

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.servicekartcustomer.extensions.IntentConstants
import com.example.servicekartcustomer.R
import com.example.servicekartcustomer.dashboard.DashBoardActivity

class OtpVerifiedSplashActivity : AppCompatActivity() {
    var fromScreen = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otp_verified)
        if(intent.getStringExtra(IntentConstants.FROM_DASHBOARD)!=null) {
            fromScreen = intent.getStringExtra(IntentConstants.FROM_DASHBOARD).toString()
        }


        val handler = Handler()
        handler.postDelayed({
            if (fromScreen == IntentConstants.FROM_LOGIN) {
                startActivity(Intent(this, DashBoardActivity::class.java))
                finishAffinity()
            } else  {
                startActivity(Intent(this, EditProfileActivity::class.java).putExtra(IntentConstants.FROM_DASHBOARD,fromScreen))
                finish()
            }

                            },500)


    }


}
