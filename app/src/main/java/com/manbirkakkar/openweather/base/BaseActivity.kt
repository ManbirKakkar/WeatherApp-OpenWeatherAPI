package com.manbirkakkar.openweather.base

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.agnext.trackingutil.permission.TrackingUtil


open class BaseActivity : AppCompatActivity() {

/*TODO MOVE TO NEXT SCREEN */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
}

fun checkLocationPermission(context: Context){
    if (!TrackingUtil(context).isLocationPermission()){
        TrackingUtil(context).requestLocationPermission()
    }
}

fun hasConnection(context: Context): Boolean {
    val cm = context.getSystemService(
        Context.CONNECTIVITY_SERVICE
    ) as ConnectivityManager
    val wifiNetwork = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
    if (wifiNetwork != null && wifiNetwork.isConnected) {
        return true
    }
    val mobileNetwork = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
    if (mobileNetwork != null && mobileNetwork.isConnected) {
        return true
    }
    val activeNetwork = cm.activeNetworkInfo
    return activeNetwork != null && activeNetwork.isConnected
}


fun printLog(message: String) {
    Log.e("TAG", "$message")
}