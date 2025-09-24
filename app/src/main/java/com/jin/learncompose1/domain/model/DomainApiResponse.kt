package com.jin.learncompose1.domain.model

import com.google.gson.annotations.SerializedName

data class DomainApiResponse(
    @SerializedName("phone")
    val phone: List<String>,
    @SerializedName("medium_museum")
    val mediumMuseum: List<String>
)
