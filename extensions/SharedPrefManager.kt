package com.example.servicekartcustomer.extensions

import android.content.Context
import android.content.SharedPreferences
import com.example.servicekartcustomer.dashboard.MyAccountFragment

object SharedPrefManager {

    lateinit var preferences: SharedPreferences
    private fun getPreference(con: Context): SharedPreferences {

        preferences = con.getSharedPreferences("1", Context.MODE_PRIVATE)

        return preferences
    }

    fun saveInt(context: Context, key: String, value: Int) {
        getPreference(context).edit().putInt(key, value).apply()
    }

    fun saveLong(context: Context, key: String, value: Long) {
        getPreference(context).edit().putLong(key, value).apply()
    }

    fun saveFloat(context: Context, key: String, value: Float) {
        getPreference(context).edit().putFloat(key, value).apply()
    }

    fun saveString(context: Context, key: String, value: String) {
        getPreference(context).edit().putString(key, value).apply()
    }

    fun saveBoolean(context: Context, key: String, status: Boolean) {
        getPreference(context).edit().putBoolean(key, status).apply()
    }

    fun getString(context: Context, key: String): String {
        return getPreference(context).getString(key, "")!!
    }

    fun getFloat(context: Context, key: String): Float {
        return getPreference(context).getFloat(key, 0F)
    }

    fun getInt(context: Context, key: String): Int {
        return getPreference(context).getInt(key, 0)
    }

    fun getLong(context: Context, key: String): Long {
        return getPreference(context).getLong(key, 0)
    }

    fun getBoolean(context: Context, key: String): Boolean {
        return getPreference(context).getBoolean(key, false)
    }

    fun getSuccessBoolean(context: Context, key: String): Boolean {
        return getPreference(context).getBoolean(key, true)
    }

    fun removeData(key: String) {
        val editor = preferences.edit().remove(key)
        editor.apply()
        editor.apply()
    }
    fun clear() {
        preferences.edit()?.clear()?.apply()
    }

    fun getLanguageString(context: Context, key: String): String {
        return getPreference(context).getString(key, "")!!
    }

}