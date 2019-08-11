package org.com.raian.nycschoolschase.ui.schools.fragments

import android.os.Bundle
import android.view.InflateException
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import org.com.raian.nycschoolschase.R
import org.com.raian.nycschoolschase.constants.GlobalConstants.Companion.latAndLong
import org.com.raian.nycschoolschase.ui.base.BaseFragment
import org.com.raian.nycschoolschase.util.extensions.checkForAccessFineLocationPermissions
import org.com.raian.nycschoolschase.util.safeLet
import java.util.logging.Logger

class MapsFragment : BaseFragment(), OnMapReadyCallback {
    init{
        TAG = MapsFragment::class.java.simpleName
        logger = Logger.getLogger(TAG)
    }

    //GoogleMaps
    private lateinit var mMap: GoogleMap
    private lateinit var mMapView: MapView

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState).also {
            mMapView.onSaveInstanceState(outState)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState).also {
            retainInstance = true
            checkForAccessFineLocationPermissions()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        try {
            rootView = inflater.inflate(R.layout.fragment_maps, container, false)
            initView()

            MapsInitializer.initialize(context)
            mMapView.onCreate(savedInstanceState)
            mMapView.getMapAsync(this)
        } catch (e: InflateException) {
            logger.severe("$TAG: ${e.message}")
        }

        return rootView
    }

    override fun initView() {
        mMapView = rootView.findViewById(R.id.map)
    }

    override fun onResume() {
        super.onResume().also {
            mMapView.onResume()
        }
    }

    override fun onLowMemory() {
        super.onLowMemory().also {
            mMapView.onLowMemory()
        }
    }

    override fun onPause() {
        super.onPause().also {
            mMapView.onPause()
        }
    }

    override fun onDestroy() {
        super.onDestroy().also {
            mMapView.onDestroy()
        }
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        mMap = googleMap ?: return
        safeLet(
            arguments?.getString(latAndLong[0])?.toDouble(),
            arguments?.getString(latAndLong[1])?.toDouble()
        ) { lat, long ->
            activity?.runOnUiThread {
                val position = LatLng(lat, long)
                mMap.addMarker(MarkerOptions().position(position).title(id.toString()))
                gotoMarker(position)
            }
        }
    }

    /**
     * Move camera to specific marker
     * **/
    private fun gotoMarker(position: LatLng) {
        with(mMap) {
            uiSettings.isZoomControlsEnabled = true
            uiSettings.isMyLocationButtonEnabled = true
            animateCamera(CameraUpdateFactory.newLatLngZoom(position, 10f))
        }
    }

}
