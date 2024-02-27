package com.example.modmobilecustomer.data.model.otpvalidationmodel


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("status")
    val status: Int,
    @SerializedName("success")
    val success: String
)