package com.solar.learncompose1.di

import com.solar.learncompose1.data.api.ApiService
import com.solar.learncompose1.data.api.RetrofitBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun retrofitService(): ApiService {
        return RetrofitBuilder.retrofitService
    }
}