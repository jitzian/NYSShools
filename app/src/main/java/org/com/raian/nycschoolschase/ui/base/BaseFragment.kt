package org.com.raian.nycschoolschase.ui.base

import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import org.com.raian.nycschoolschase.ui.schools.viewmodel.SchoolsViewModel
import org.com.raian.nycschoolschase.ui.viewmodels.ViewModelFactory
import org.com.raian.nycschoolschase.ui.weather.viewmodel.LocationViewModel
import java.util.logging.Logger

abstract class BaseFragment : Fragment() {
    protected lateinit var TAG: String
    protected lateinit var logger: Logger
    protected lateinit var rootView: View

    protected val locationViewModel by lazy {
        ViewModelProviders.of(this, context?.let { ViewModelFactory(it) }).get(LocationViewModel::class.java)
    }

    protected val schoolsViewModel by lazy {
        ViewModelProviders.of(this, context?.let { ViewModelFactory(it) }).get(SchoolsViewModel::class.java)
    }

    abstract fun initView()

}