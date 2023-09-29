package com.example.servicekartcustomer.extensions

import android.content.Context
import com.example.servicekartcustomer.authentication.EditProfileActivity
import com.example.servicekartcustomer.authentication.SplashActivity
import com.example.servicekartcustomer.dashboard.DashBoardActivity
import com.example.servicekartcustomer.dashboard.HomeFragment

class SetGetData {
        companion object {
            fun setToken(c: Context, Token: String) {
                SharedPrefManager.saveString(c, "Token",Token )
            }

            fun getToken(c: Context): String {
                return SharedPrefManager.getString(c, "Token")
            }

            fun setEmail(c: Context, email: String) {
                SharedPrefManager.saveString(c, "email", email)
            }

            fun getEmail(c: Context): String {
                return SharedPrefManager.getString(c, "email")
            }

            fun setPhone(c: Context, phone: String) {
                SharedPrefManager.saveString(c, "phone", phone)
            }

            fun getPhone(c: Context): String {
                return SharedPrefManager.getString(c, "phone")
            }

            fun setUserId(c: Context, userId: String){
                SharedPrefManager.saveString(c, "userId", userId)
            }
            fun getUserId(c: Context): String {
                return SharedPrefManager.getString(c , "userId")
            }
            fun setProfilePic(c: Context, profilePic: String){
                SharedPrefManager.saveString(c, "profilePic", profilePic)
            }
            fun getProfilePic(c: Context): String {
                return SharedPrefManager.getString(c, "profilePic")
            }
            fun setName(c: Context, name: String){
                SharedPrefManager.saveString(c, "name", name)
            }
            fun getName(c: Context): String {
                return SharedPrefManager.getString(c, "name")
            }

            fun setFlag(c: Context, flag: Boolean) {
                SharedPrefManager.saveBoolean(c, "flag",flag)
            }

            fun getFlag(c: Context): Boolean {
                return SharedPrefManager.getBoolean(c, "flag")
            }

        }


    }

