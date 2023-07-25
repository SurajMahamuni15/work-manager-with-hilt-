package com.example.workmanagerdemo

import com.example.workmanagerdemo.Constance.BASE_URL
import com.example.workmanagerdemo.api.ProductApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun retrofitProvider(): Retrofit.Builder {
        return Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
    }

    @Provides
    @Singleton
    fun getRetrofitApi(): ProductApi {
        return retrofitProvider().build().create(ProductApi::class.java)
    }


}