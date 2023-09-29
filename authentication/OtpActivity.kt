package com.example.servicekartcustomer.authentication

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.servicekartcustomer.R
import com.example.servicekartcustomer.databinding.ActivityOtpBinding
import com.example.servicekartcustomer.extensions.*
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_otp.*
import java.util.concurrent.TimeUnit

class OtpActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOtpBinding
    var text = ""
    var fromScreen = ""
    var userId = ""
    var otpId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_otp)
        super.onCreate(savedInstanceState)
        if (intent.getStringExtra(IntentConstants.FROM_DASHBOARD) != null) {
            fromScreen = intent.getStringExtra(IntentConstants.FROM_DASHBOARD).toString()
        }

        userId = intent.getStringExtra(IntentConstants.FROM_OTP).toString()

        FirebaseApp.initializeApp(this)
        FirebaseAuth.getInstance()
        otpTimer()
        mobile()
        initiateOtp()
        initClicks()
        initTextChanged()
        closeKeyBoard()
    }

    private fun mobile() {
        if (intent.getStringExtra(IntentConstants.FROM_MOBILE_NUMBER) != null) {
            text = intent.getStringExtra(IntentConstants.FROM_MOBILE_NUMBER).toString()
        }


        tvNumber.text = text
    }

    private fun initiateOtp() {
        val options = PhoneAuthOptions.newBuilder(Firebase.auth)
            .setPhoneNumber("+91$text")
            .setTimeout(30L, TimeUnit.SECONDS)
            .setActivity(this)
            .setCallbacks(object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                override fun onCodeSent(
                    s: String,
                    forceResendingToken: PhoneAuthProvider.ForceResendingToken
                ) {
                    try {
                        otpId = s
                    } catch (e: Exception) {
                        e.printStackTrace()
                        Toast.makeText(applicationContext, e.message, Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onVerificationCompleted(phoneAuthCredential: PhoneAuthCredential) {
                    signInWithPhoneAuthCredential(phoneAuthCredential)

                    phoneAuthCredential.smsCode
//                    startActivity(Intent(this, OtpVerifiedSplashActivity::class.java)
//                        .putExtra(IntentConstants.FROM_SCREEN,IntentConstants.FROM_DASHBOARD))

                }

                override fun onVerificationFailed(e: FirebaseException) {
                    Toast.makeText(applicationContext, e.message, Toast.LENGTH_LONG).show()
                }
            })
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)// OnVerificationStateChangedCallbacks
    }

    fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        Firebase.auth.signInWithCredential(credential)
            .addOnCompleteListener(
                this
            ) { task ->

                if (task.isSuccessful) {
                    startActivity(Intent(this, OtpVerifiedSplashActivity::class.java).putExtra(IntentConstants.FROM_DASHBOARD,fromScreen))
                    finishAffinity()
                } else {
                    Toast.makeText(
                        applicationContext,
                        "Please enter valid otp",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

    private fun otpTimer() {
        object : CountDownTimer(30000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                binding.tvTimer.text = getString(R.string.remaining)+ millisUntilFinished / 1000
                binding.tvRecode.invisible()
            }
            override fun onFinish() {
                binding.tvTimer.invisible()
                binding.tvRecode.visible()
            }
        }
            .start()
    }

    private fun initTextChanged() {
    binding.etOtp1.addTextChangedListener(GenericTextWatcher(binding.etOtp1, binding.etOtp2))
        binding.etOtp2.addTextChangedListener(GenericTextWatcher(binding.etOtp2, binding.etOtp3))
        binding.etOtp3.addTextChangedListener(GenericTextWatcher(binding.etOtp3, binding.etOtp4))
        binding.etOtp4.addTextChangedListener(GenericTextWatcher(binding.etOtp4, binding.etOtp5))
        binding.etOtp5.addTextChangedListener(GenericTextWatcher(binding.etOtp5, binding.etOtp6))
        binding.etOtp6.addTextChangedListener(GenericTextWatcher(binding.etOtp6, null))



//GenericKeyEvent here works for deleting the element and to switch back to previous EditText
//first parameter is the current EditText and second parameter is previous EditText
        binding.etOtp1.setOnKeyListener(GenericKeyEvent(binding.etOtp1, null))
        binding.etOtp2.setOnKeyListener(GenericKeyEvent(binding.etOtp2, binding.etOtp1))
        binding.etOtp3.setOnKeyListener(GenericKeyEvent(binding.etOtp3, binding.etOtp2))
        binding.etOtp4.setOnKeyListener(GenericKeyEvent(binding.etOtp4,binding.etOtp3))
        binding.etOtp5.setOnKeyListener(GenericKeyEvent(binding.etOtp5,binding.etOtp4))
        binding.etOtp6.setOnKeyListener(GenericKeyEvent(binding.etOtp6,binding.etOtp5))
    }

    private fun initClicks() {
        binding.ivBackArrow.setOnClickListener {
            onBackPressed()
        }
        btnVerify.setOnClickListener {
            binding.pBar.visible()
            val otp =
                "${binding.etOtp1.text}${binding.etOtp2.text}${binding.etOtp3.text}${binding.etOtp4.text}${binding.etOtp5.text}${binding.etOtp6.text}"
            when {
                otp.isEmpty() -> {
                    pBar.invisible()
                    Toast.makeText(this, "Invalid Otp", Toast.LENGTH_SHORT)
                        .show()
                }
                otp.length != 6 -> {
                    pBar.invisible()
                    Toast.makeText(this, "Invalid Otp", Toast.LENGTH_SHORT)
                        .show()
                }
                else -> {
                    val credential = PhoneAuthProvider.getCredential(otpId!!, otp)
//                    println("OtpActivity.initClicks: ${"credential"}")
                    signInWithPhoneAuthCredential(credential)
                    SetGetData.setUserId(this,userId)


                }
            }
        }
        binding.pBar.gone()
    }

    private fun closeKeyBoard() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
        binding.tvRecode.setOnClickListener {
            binding.pBar.gone()
            binding.etOtp1.text.clear()
            binding.etOtp2.text.clear()
            binding.etOtp3.text.clear()
            binding.etOtp4.text.clear()
            binding.etOtp5.text.clear()
            binding.etOtp6.text.clear()
            binding.etOtp1.clearFocus()
            binding.etOtp2.clearFocus()
            binding.etOtp3.clearFocus()
            binding.etOtp4.clearFocus()
            binding.etOtp5.clearFocus()
            binding.etOtp6.clearFocus()
            initiateOtp()
            otpTimer()
            binding.tvTimer.visible()
            binding.tvRecode.visible()

        }
    }






    class GenericKeyEvent internal constructor(private val currentView: EditText, private val previousView: EditText?) : View.OnKeyListener{
        override fun onKey(p0: View?, keyCode: Int, event: KeyEvent?): Boolean {
            if(event!!.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_DEL && currentView.id != R.id.etOtp1 && currentView.text.isEmpty()) {
                //If current is empty then previous EditText's number will also be deleted
                previousView!!.text = null
                previousView.requestFocus()
                return true
            }
            return false
        }


    }

    class GenericTextWatcher internal constructor(private val currentView: View, private val nextView: View?) : TextWatcher {
        override fun afterTextChanged(editable: Editable) { // TODO Auto-generated method stub
            val text = editable.toString()
            when (currentView.id) {
                R.id.etOtp1 -> if (text.length == 1) nextView!!.requestFocus()
                R.id.etOtp2 -> if (text.length == 1) nextView!!.requestFocus()
                R.id.etOtp3 -> if (text.length == 1) nextView!!.requestFocus()
                R.id.etOtp4 -> if (text.length == 1) nextView!!.requestFocus()
                R.id.etOtp5 -> if (text.length == 1) nextView!!.requestFocus()
                //You can use EditText4 same as above to hide the keyboard
            }
        }

        override fun beforeTextChanged(
            arg0: CharSequence,
            arg1: Int,
            arg2: Int,
            arg3: Int
        ) {
        }

        override fun onTextChanged(
            arg0: CharSequence,
            arg1: Int,
            arg2: Int,
            arg3: Int
        ) {
        }

    }
}










