package org.com.raian.nycschoolschase.ui.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import org.com.raian.nycschoolschase.constants.GlobalConstants.Companion.dataBaseName
import org.com.raian.nycschoolschase.database.SchoolDataBase
import org.com.raian.nycschoolschase.ui.schools.viewmodel.SchoolsViewModel
import org.com.raian.nycschoolschase.ui.weather.viewmodel.LocationViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(private val context: Context): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(SchoolsViewModel::class.java)){
            val db = Room.databaseBuilder(context, SchoolDataBase::class.java, dataBaseName).build()
            return SchoolsViewModel(db, context) as T
        }
        if(modelClass.isAssignableFrom(LocationViewModel::class.java)){
            return LocationViewModel() as T
        }
        throw IllegalArgumentException ("View Model Class is not defined")
    }
}