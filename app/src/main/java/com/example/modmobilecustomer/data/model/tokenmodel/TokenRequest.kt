package com.example.modmobilecustomer.data.model.tokenmodel

import com.google.gson.annotations.SerializedName

data class TokenRequest(
    @SerializedName("user")
    val user: String,
    @SerializedName("pass")
    val pass: String,
)