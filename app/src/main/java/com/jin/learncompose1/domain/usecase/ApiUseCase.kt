package com.jin.learncompose1.domain.usecase

import com.jin.learncompose1.domain.repository.ApiRepository
import javax.inject.Inject

class ApiUseCase @Inject constructor(
    private val apiRepository: ApiRepository
) {
    suspend fun getData() = apiRepository.getData()

    suspend fun updateData() = apiRepository.updateData()
}