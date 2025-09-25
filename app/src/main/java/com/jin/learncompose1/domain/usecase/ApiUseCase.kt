package com.jin.learncompose1.domain.usecase

import com.jin.learncompose1.domain.repository.ApiRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class ApiUseCase @Inject constructor(
    private val apiRepository: ApiRepository
) {
    suspend fun getData() = apiRepository.getData()

    suspend fun updateData() = apiRepository.updateData()

    suspend fun getDataPhotoAndMediumMuseum(): Pair<Any, Any> {
        return coroutineScope {
            val job1 = async { apiRepository.getData() }
            val job2 = async { apiRepository.updateData() }
            Pair(job1.await(), job2.await())
        }
    }
}