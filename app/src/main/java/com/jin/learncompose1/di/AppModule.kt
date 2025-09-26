package com.jin.learncompose1.di

import android.content.Context
import com.jin.learncompose1.data.api.ApiService
import com.jin.learncompose1.data.api.RetrofitBuilder
import com.jin.learncompose1.domain.repository.ApiRepository
import com.jin.learncompose1.domain.repository.ApiRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun retrofitService(
        retrofit: Retrofit
    ): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideRetrofitBuilder(
        @ApplicationContext appContext: Context
    ): Retrofit {
        return RetrofitBuilder.provideRetrofit(appContext)
    }
}

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindApiRepository(
        apiRepositoryImpl: ApiRepositoryImpl
    ): ApiRepository
}