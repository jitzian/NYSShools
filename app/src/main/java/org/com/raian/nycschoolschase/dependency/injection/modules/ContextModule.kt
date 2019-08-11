package org.com.raian.nycschoolschase.dependency.injection.modules

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.Reusable

@Module
class ContextModule(private val context: Context){

    @Provides
    @Reusable
    fun providesContext(): Context{
        return context
    }
}