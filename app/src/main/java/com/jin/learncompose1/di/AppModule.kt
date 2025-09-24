package com.jin.learncompose1.di

import com.jin.learncompose1.data.api.ApiService
import com.jin.learncompose1.data.api.RetrofitBuilder
import com.jin.learncompose1.domain.repository.ApiRepository
import com.jin.learncompose1.domain.repository.ApiRepositoryImpl
import dagger.Binds
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

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Provides
    abstract fun bindApiRepository(
        apiRepositoryImpl: ApiRepositoryImpl
    ): ApiRepository
}