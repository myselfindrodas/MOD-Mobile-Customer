package com.example.modmobilecustomer.data.model.otpsentmodel


import com.google.gson.annotations.SerializedName

data class SentOTPResponse(
    @SerializedName("request_id")
    val requestId: String,
    @SerializedName("type")
    val type: String
)