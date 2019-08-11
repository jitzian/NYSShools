package org.com.raian.nycschoolschase.ui.activities

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
import android.os.Looper
import android.provider.Settings
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.*
import org.com.raian.nycschoolschase.R
import org.com.raian.nycschoolschase.ui.base.BaseActivity
import org.com.raian.nycschoolschase.ui.schools.fragments.SchoolsFragment
import org.com.raian.nycschoolschase.ui.weather.fragments.WeatherFragment
import org.com.raian.nycschoolschase.util.network.NetworkReceiver
import java.util.logging.Logger

class MainActivity : BaseActivity(), NetworkReceiver.NetworkListener {
    init {
        TAG = MainActivity::class.java.simpleName
        logger = Logger.getLogger(TAG)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState).also {
            setContentView(R.layout.activity_main)
            initViews()
        }
    }

    @SuppressLint("RestrictedApi")
    override fun initViews() {
        mLocationRequest = LocationRequest()
        val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            buildAlertMessageNoGps()
        }

        if (checkPermissionForLocation(this)) {
            startLocationUpdates()
        }
    }

    override fun getNetworkStatus(isConnected: Boolean) {
        try {
            if (!isConnected) {
                logger.severe("$TAG::getNetworkStatus::NO::$isConnected")
                showNoConnectivityMessage()

            } else {
                logger.info("$TAG::getNetworkStatus::YES::$isConnected")
                supportFragmentManager
                    .beginTransaction()
                    .add(R.id.mFrameLayoutWeatherContainer, WeatherFragment(), WeatherFragment::class.java.simpleName)
                    .add(R.id.mFrameLayoutSchoolsContainer, SchoolsFragment(), SchoolsFragment::class.java.simpleName)
                    .commit()

            }
        } catch (rte: RuntimeException) {
            logger.severe("$TAG::getNetworkStatus::${rte.message}")
        }
    }

    private fun buildAlertMessageNoGps() {
        val builder = AlertDialog.Builder(this)
        builder.setMessage(resources.getString(R.string.message_gps_not_enabled))
            .setCancelable(false)
            .setPositiveButton(resources.getString(R.string.message_Yes)) { _, _ ->
                startActivityForResult(
                    Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                    , 11
                )
            }
            .setNegativeButton("No") { dialog, id ->
                dialog.cancel()
                finish()
            }
        val alert: AlertDialog = builder.create()
        alert.show()
    }


    private fun startLocationUpdates() {
        // Create the location request to start receiving updates

        mLocationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
//        mLocationRequest.interval = INTERVAL
//        mLocationRequest.fastestInterval = FASTEST_INTERVAL

        // Create LocationSettingsRequest object using location request
        val builder = LocationSettingsRequest.Builder()
        builder.addLocationRequest(mLocationRequest)
        val locationSettingsRequest = builder.build()

        val settingsClient = LocationServices.getSettingsClient(this)
        settingsClient.checkLocationSettings(locationSettingsRequest)

        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        // new Google API SDK v11 uses getFusedLocationProviderClient(this)
        if (ActivityCompat.checkSelfPermission(
                this,
                ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        mFusedLocationProviderClient!!.requestLocationUpdates(
            mLocationRequest, mLocationCallback,
            Looper.myLooper()
        )
    }

    private val mLocationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            // do work here
            locationResult.lastLocation
            onLocationChanged(locationResult.lastLocation)
        }
    }

    fun onLocationChanged(location: Location) {
        // New location has now been determined

        mLastLocation = location
        logger.severe("$TAG::onLocationChanged::${mLastLocation.latitude}")
        logger.severe("$TAG::onLocationChanged::${mLastLocation.longitude}")
        stopLocationUpdates()
    }

    private fun stopLocationUpdates() {
        mFusedLocationProviderClient?.removeLocationUpdates(mLocationCallback)
    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == REQUEST_PERMISSION_LOCATION) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startLocationUpdates()
            } else {
                Toast.makeText(this@MainActivity, "Permission Denied", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun checkPermissionForLocation(context: Context): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (context.checkSelfPermission(ACCESS_FINE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED
            ) {
                true
            } else {
                // Show the permission request
                ActivityCompat.requestPermissions(
                    this, arrayOf(ACCESS_FINE_LOCATION),
                    REQUEST_PERMISSION_LOCATION
                )
                false
            }
        } else {
            true
        }
    }

}
