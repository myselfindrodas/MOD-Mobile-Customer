package com.example.modmobilecustomer.data.model.tokenmodel


import com.google.gson.annotations.SerializedName

data class TokenResponse(
    @SerializedName("response")
    val response: Response
)