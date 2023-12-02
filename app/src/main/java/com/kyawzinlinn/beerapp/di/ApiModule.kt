package com.kyawzinlinn.beerapp.di

import com.kyawzinlinn.beerapp.data.remote.ApiService
import com.kyawzinlinn.beerapp.utils.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    fun providesApiService(): ApiService {
        val retrofit =
            Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
                .build()

        return retrofit.create(ApiService::class.java)
    }
}