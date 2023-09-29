package com.example.servicekartcustomer.authentication

import android.content.Intent
import android.graphics.Point
import android.os.Bundle
import android.text.SpannableString
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.text.style.UnderlineSpan
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import android.widget.ScrollView
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.servicekartcustomer.R
import com.example.servicekartcustomer.databinding.ActivityLogInMobileBinding
import com.example.servicekartcustomer.extensions.*
import com.facebook.*
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.android.synthetic.main.activity_log_in_mobile.*
import org.json.JSONException


class LoginActivity : BaseActivity(), View.OnClickListener {

    private lateinit var mGoogleSignInClient: GoogleSignInClient
    private lateinit var binding: ActivityLogInMobileBinding
    private val Req_Code: Int = 123
    private lateinit var callbackManager: CallbackManager
    private lateinit var loginViewModel: LoginViewModel


    private val auth by lazy {
        FirebaseAuth.getInstance()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_log_in_mobile)
        FirebaseApp.initializeApp(this)
        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        initClicks()
        setSpannable()
        googleAuth()
        setUpLoginViewModel()

    }

    override fun onBackPressed() {
        super.onBackPressed()
        finishAffinity()
    }
    //FacebookAuthentication

    private fun facebookLoginManager() {
        FacebookSdk.setClientToken(this.toString())
        FacebookSdk.sdkInitialize(this)
        LoginManager.getInstance().logInWithReadPermissions(this, listOf("public_profile", "email"))
        callbackManager = CallbackManager.Factory.create()
        LoginManager.getInstance().registerCallback(callbackManager,
            object : FacebookCallback<LoginResult> {
                override fun onCancel() {

                }

                override fun onError(error: FacebookException) {

                }

                override fun onSuccess(result: LoginResult) {
                    requestUserProfile(result)
                    val request = GraphRequest.newMeRequest(result.accessToken) { obj, _ ->
                        try {
                        } catch (e: JSONException) {
                            e.printStackTrace()
                        } catch (e: NullPointerException) {
                            e.printStackTrace()
                        }
                    }
                    val parameters = Bundle()
                    parameters.putString(
                        "fields", "id, name, email, gender, birthday"
                    )
                    request.parameters = parameters
                    request.executeAsync()
                }
            })
    }

    fun requestUserProfile(loginResult: LoginResult) {
        val request = GraphRequest.newMeRequest(
            loginResult.accessToken
        ) { _, response ->
            try {
                if (response != null) {
                    Log.d("FACEBOOK_USER_RESPONSE:", response.toString())
                    val socialId = response.jsonObject?.get("id").toString()
                    var firstName = ""
                    var lastName = ""
                    var email = ""
                    var userPicUrl = ""
                    if (response.jsonObject?.has("email") == true) {
                        email = response.jsonObject?.get("email").toString()
                    }
                    if (response.jsonObject?.has("first_name") == true) {
                        firstName = response.jsonObject?.get("first_name").toString()
                    }
                    if (response.jsonObject?.has("last_name") == true) {
                        lastName = response.jsonObject?.get("last_name").toString()
                    }
                    if (response.jsonObject?.has("picture") == true) {
                        val facebookPictureObject = response.jsonObject?.getJSONObject("picture")
                        if (facebookPictureObject != null && facebookPictureObject.has("data")) {
                            val facebookDataObject = facebookPictureObject.getJSONObject("data")
                            if (facebookDataObject.has("url")) {
                                userPicUrl = facebookDataObject.getString("url")
                            }
                        }
                    }
                }
            } catch (e: JSONException) {
                Log.d("FACEBOOKPARSEEXCEPTION", e.message.toString())
            }
        }
        val parameters = Bundle()
        parameters.putString("fields", "id, first_name, last_name, picture, email")
        request.parameters = parameters
        request.executeAsync()
    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        updateUI(currentUser)
    }



    private fun handleFacebookAccessToken(token: AccessToken) {
        Log.d(TAG, "handleFacebookAccessToken:$token")

        val credential = FacebookAuthProvider.getCredential(token.token)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithCredential:success")
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithCredential:failure", task.exception)
                    Toast.makeText(
                        baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()
                    updateUI(null)
                }
            }
    }
    // [END auth_with_facebook]

    private fun updateUI(user: FirebaseUser?) {


    }

    companion object {
        private const val TAG = "FacebookLogin"
    }


//phone Number validation

    private fun callLogin(phone: String) {

    }

    private fun initClicks() {
        binding.ivBlueTick.setOnClickListener(this)

        binding.btnFacebook.setOnClickListener {
            facebookLoginManager()
        }
        binding.etMobileNumber.setOnClickListener {
            scrollToView(binding.scrollLogIn, binding.etMobileNumber)
        }
    }

    private fun setSpannable() {
        var ss: SpannableString? = null
        ss =
            SpannableString(getString(R.string.by_accepting_all_terms_and_conditions))

        val terms: ClickableSpan = object : ClickableSpan() {
            override fun onClick(textView: View) {
                startActivity(Intent(this@LoginActivity, WebViewActivity::class.java))
            }
        }

        val conditions: ClickableSpan = object : ClickableSpan() {
            override fun onClick(textView: View) {
                startActivity(Intent(this@LoginActivity, WebViewActivity::class.java))


            }
        }


        ss.setSpan(terms, 17, 23, 0)
        ss.setSpan(ForegroundColorSpan(resources.getColor(R.color.blue)), 18, 23, 0)

        ss.setSpan(UnderlineSpan(), 17, 23, 0)




        ss.setSpan(conditions, 27, ss.toString().length, 0)
        ss.setSpan(
            ForegroundColorSpan(resources.getColor(R.color.blue)),
            27,
            ss.toString().length,
            0
        )

        ss.setSpan(UnderlineSpan(), 28, ss.toString().length, 0)


        binding.tvTermsAndConditions.movementMethod = LinkMovementMethod.getInstance()
        binding.tvTermsAndConditions.setText(ss, TextView.BufferType.SPANNABLE)


    }


    //Google
    private fun googleAuth() {
        FirebaseApp.initializeApp(this)

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_idd))
            .requestEmail()
            .build()

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)

        btnGoogle.setOnClickListener { view: View? ->
            Toast.makeText(this, "Logging In", Toast.LENGTH_SHORT).show()
            signInGoogle()
        }
    }

    private fun signInGoogle() {
        val signInIntent: Intent = mGoogleSignInClient.signInIntent
        startActivityForResult(signInIntent, Req_Code)
    }

    // onActivityResult() function : this is where
// we provide the task and data for the Google Account
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == Req_Code) {
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleResult(task)
        } else {
            callbackManager.onActivityResult(requestCode, resultCode, data)
        }
    }
    private fun handleResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account: GoogleSignInAccount? = completedTask.getResult(ApiException::class.java)
            if (account != null) {
                UpdateUI(account)
            }
        } catch (e: ApiException) {
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    // this is where we update the UI after Google signIn takes place
    private fun UpdateUI(account: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(account.idToken, null)
        auth.signInWithCredential(credential).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                SavedPreference.setEmail(this, account.email.toString())
                SavedPreference.setUsername(this, account.displayName.toString())
                val intent = Intent(this, Profile::class.java)
                startActivity(intent)
            }
        }


        fun onStart() {
            super.onStart()
            if (GoogleSignIn.getLastSignedInAccount(this) != null) {
                startActivity(
                    Intent(
                        this, LogoutActivity
                        ::class.java
                    )
                )
                finishAffinity()
            }
        }

    }

    private fun setUpLoginViewModel() {
        loginViewModel.loginData.observe(this) {

            if (it.status == 200){
//                SetGetData.setUserId(this, it.user._id)
                Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                SetGetData.setName(this, it.user.firstName)
                SetGetData.setProfilePic(this, it.user.photo)
                SetGetData.setEmail(this, it.user.email)
                SetGetData.setPhone(this, it.user.phone)
                SetGetData.setToken(this, it.user.token)
                val intent = Intent(this, OtpActivity::class.java)
                intent.putExtra(IntentConstants.FROM_DASHBOARD,IntentConstants.FROM_LOGIN)
                intent.putExtra(IntentConstants.FROM_MOBILE_NUMBER,etMobileNumber.text.toString())
                intent.putExtra(IntentConstants.FROM_OTP,it.user.id)
                startActivity(intent)
            }else if(it.status== 201){
                Toast.makeText(this,it.message, Toast.LENGTH_SHORT).show()
                val intent = Intent(this, OtpActivity::class.java)
                intent.putExtra(IntentConstants.FROM_DASHBOARD,IntentConstants.FROM_SIGNUP)
                intent.putExtra(IntentConstants.FROM_MOBILE_NUMBER,etMobileNumber.text.toString())
                startActivity(intent)
            }

        }

        loginViewModel.validationErr.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }


        loginViewModel.responseError.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }
    }
    private fun scrollToView(scrollViewParent: ScrollView, view: View) {
        // Get deepChild Offset
        val childOffset = Point()
        getDeepChildOffset(scrollViewParent, view.parent, view, childOffset)
        // Scroll to child.
        scrollViewParent.smoothScrollTo(0, childOffset.y)

    }
    private fun getDeepChildOffset(
        mainParent: ViewGroup,
        parent: ViewParent,
        child: View,
        accumulatedOffset: Point
    ) {
        val parentGroup = parent as ViewGroup
        accumulatedOffset.x += child.left
        accumulatedOffset.y += child.top
        if (parentGroup == mainParent) {
            return
        }
        getDeepChildOffset(mainParent, parentGroup.parent, parentGroup, accumulatedOffset)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.ivBlueTick -> {
                if (::loginViewModel.isInitialized) {
                    binding.pBar.visible()
                    loginViewModel.login(etMobileNumber.text.trim().toString())
                }
                binding.pBar.gone()
            }
        }
    }
}









