package org.com.raian.nycschoolschase.ui.base

import android.content.IntentFilter
import android.location.Location
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationRequest
import com.google.android.material.snackbar.Snackbar
import org.com.raian.nycschoolschase.R
import org.com.raian.nycschoolschase.util.network.NetworkReceiver
import java.util.logging.Logger

abstract class BaseActivity : AppCompatActivity(), NetworkReceiver.NetworkListener {
    internal lateinit var logger: Logger
    internal lateinit var TAG: String
    private lateinit var mSnackBar: Snackbar
    private lateinit var networkReceiver: NetworkReceiver

    //Location
    protected var mFusedLocationProviderClient: FusedLocationProviderClient? = null
    protected val INTERVAL: Long = 2000
    protected val FASTEST_INTERVAL: Long = 1000
    protected lateinit var mLastLocation: Location
    protected lateinit var mLocationRequest: LocationRequest
    protected val REQUEST_PERMISSION_LOCATION = 10

    abstract fun initViews()

    override fun onStart() {
        super.onStart().also {
            networkReceiver = NetworkReceiver(this)
            registerReceiver(networkReceiver, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
        }
    }

    override fun onStop() {
        super.onStop().also {
            unregisterReceiver(networkReceiver)
        }
    }

    fun showNoConnectivityMessage() {
        mSnackBar = Snackbar.make(
            findViewById(R.id.mLayoutMainActivityContainer),
            getString(R.string.NoConnectivityMessage),
            Snackbar.LENGTH_LONG
        ) //Assume "rootLayout" as the root layout of every activity.
        mSnackBar.show()
    }

    override fun onDestroy() {
        super.onDestroy().also {
            viewModelStore.clear()
        }
    }
}