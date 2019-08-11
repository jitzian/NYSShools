package org.com.raian.nycschoolschase.ui.activities

import android.os.Bundle
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

    override fun initViews() {
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
}
