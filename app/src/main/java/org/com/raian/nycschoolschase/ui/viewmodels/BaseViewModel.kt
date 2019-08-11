package org.com.raian.nycschoolschase.ui.viewmodels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.SupervisorJob
import org.com.raian.nycschoolschase.rest.RestApi
import java.util.logging.Logger

abstract class BaseViewModel : ViewModel(){
    internal lateinit var TAG: String
    internal lateinit var logger: Logger
    protected lateinit var restApi: RestApi
    protected val supervisorJob = SupervisorJob()
}