package com.jin.learncompose1.domain.repository

import com.jin.learncompose1.domain.model.DomainApiResponse

interface ApiRepository {
    suspend fun getData(): DomainApiResponse

    suspend fun updateData()
}