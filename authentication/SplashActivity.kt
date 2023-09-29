package com.example.servicekartcustomer.authentication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.servicekartcustomer.R
import com.example.servicekartcustomer.dashboard.DashBoardActivity
import com.example.servicekartcustomer.extensions.SetGetData

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

//        startActivity(Intent(this,LoginActivity::class.java))
        val userid = SetGetData.getUserId(this)
        Handler(Looper.getMainLooper()).postDelayed({
            if (userid.isNotEmpty()) {
                startActivity(Intent(this, DashBoardActivity::class.java))
            }
            else{
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }

            finish()
        }, 500)
    }
}