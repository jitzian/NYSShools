package org.com.raian.nycschoolschase.util.extensions

import android.annotation.SuppressLint
import androidx.fragment.app.Fragment

@SuppressLint("NewApi")
fun Fragment.checkForAccessFineLocationPermissions(){
    val permissions = arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION)
    this.requestPermissions(permissions, 0)
}

fun Fragment.checkForLocationServicePermissions(){
    val permissions = arrayOf(android.Manifest.permission.ACCESS_COARSE_LOCATION)
    this.requestPermissions(permissions, 0)
}