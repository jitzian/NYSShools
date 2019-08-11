package org.com.raian.nycschoolschase.dependency.injection.modules

import dagger.Module
import dagger.Provides
import dagger.Reusable
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkModule(private var endPoint: String) {

    @Provides
    @Reusable
    fun providesRetrofit(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(endPoint)
            .build()
    }

}