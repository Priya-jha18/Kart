package com.example.servicekartcustomer.extensions

import android.util.Log
import android.view.View
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}
fun parseDateToddMMyyyy(time: String): String? {
    val outputPattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ"
    val inputPattern = "dd-MM-yyyy"
    val inputFormat = SimpleDateFormat(inputPattern, Locale.getDefault())
    val outputFormat = SimpleDateFormat(outputPattern, Locale.getDefault())
    inputFormat.timeZone = TimeZone.getTimeZone("UTC")
    outputFormat.timeZone = TimeZone.getDefault()
    var date: Date? = null
    var str: String? = null
    //1998-11-26T00:00:00.000Z
    try {
        date = inputFormat.parse(time)
        str = outputFormat.format(date)
    } catch (e: ParseException) {
        e.printStackTrace()
    }
    Log.d("loginMessage",str.toString())
    return str
}

