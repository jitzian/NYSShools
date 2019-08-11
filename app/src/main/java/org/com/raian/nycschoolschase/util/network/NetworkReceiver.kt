package org.com.raian.nycschoolschase.util.network

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
class NetworkReceiver(networkListener: NetworkListener) : BroadcastReceiver() {

    private var networkListener: NetworkListener? = networkListener

    //For making this reusable. In this case, it is not required due to I wired it to the Abstract BaseActivity
    fun setNetworkListener(networkListener: NetworkListener) {
        this.networkListener = networkListener
    }

    interface NetworkListener {
        fun getNetworkStatus(isConnected: Boolean)
    }

    override fun onReceive(context: Context, intent: Intent) {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        networkListener?.getNetworkStatus(isConnectionAvailable(connectivityManager.activeNetworkInfo))
    }

    //Sorry for the deprecation
    private fun isConnectionAvailable(networkInfo: NetworkInfo?): Boolean {
        return networkInfo != null && networkInfo.isConnectedOrConnecting
    }
}