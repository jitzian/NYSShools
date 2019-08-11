package org.com.raian.nycschoolschase.ui.weather.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import org.com.raian.nycschoolschase.R
import org.com.raian.nycschoolschase.ui.base.BaseFragment
import org.com.raian.nycschoolschase.ui.weather.viewmodel.LocationViewModel
import org.com.raian.nycschoolschase.ui.viewmodels.ViewModelFactory
import org.com.raian.nycschoolschase.util.safeLet
import java.util.logging.Logger

class WeatherFragment : BaseFragment() {
    private lateinit var mTextViewRegionValue: TextView
    private lateinit var mTextViewTemperatureValue: TextView
    private lateinit var mImageViewWeatherIcon: ImageView

    init {
        TAG = WeatherFragment::class.java.simpleName
        logger = Logger.getLogger(TAG)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState).also {
            retainInstance = true
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.fragment_weather, container, false)
        initView()
        return rootView
    }

    override fun initView() {
        safeLet(arguments?.getDouble("latitude"), arguments?.getDouble("longitude")) { lat, long ->
            locationViewModel.getRemoteData("$lat,$long")
            mImageViewWeatherIcon = rootView.findViewById(R.id.mImageViewWeatherIcon)
            mTextViewRegionValue = rootView.findViewById(R.id.mTextViewRegionValue)
            mTextViewTemperatureValue = rootView.findViewById(R.id.mTextViewTemperatureValue)
        }
    }

    override fun onResume() {
        super.onResume().also {
            prepareObservers()
        }
    }

    private fun prepareObservers() {
        locationViewModel.getRegion().observe(this, Observer {
            mTextViewRegionValue.text = it
        })

        locationViewModel.getTemperatureInCelsius().observe(this, Observer {
            mTextViewTemperatureValue.text = it
        })
    }
}
