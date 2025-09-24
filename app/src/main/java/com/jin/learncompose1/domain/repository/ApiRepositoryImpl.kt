package com.jin.learncompose1.domain.repository

import com.jin.learncompose1.data.api.ApiService
import com.jin.learncompose1.domain.model.DomainApiResponse
import javax.inject.Inject

class ApiRepositoryImpl @Inject constructor(
    val apiService: ApiService
) : ApiRepository {

    override suspend fun getData(): DomainApiResponse {
        val dataResponse = apiService.getData()
        return DomainApiResponse(
            phone = dataResponse.phone,
            mediumMuseum = dataResponse.medium_museum
        )
    }

    override suspend fun updateData() {
        //nothing
    }
}