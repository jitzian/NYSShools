package org.com.raian.nycschoolschase.dependency.injection.components

import dagger.Component
import org.com.raian.nycschoolschase.dependency.injection.modules.ContextModule
import org.com.raian.nycschoolschase.dependency.injection.modules.NetworkModule
import org.com.raian.nycschoolschase.ui.weather.viewmodel.LocationViewModel
import org.com.raian.nycschoolschase.ui.schools.viewmodel.SchoolsViewModel

@Component(
    modules = [
        NetworkModule::class,
        ContextModule::class
    ]
)
interface ComponentInjector {

    fun inject(schoolsViewModel: SchoolsViewModel)
    fun inject(locationViewModel: LocationViewModel)

    @Component.Builder
    interface Builder {
        fun build(): ComponentInjector
        fun networkModule(networkModule: NetworkModule): Builder
        fun contextModule(contextModule: ContextModule): Builder
    }

}