package com.example.modmobilecustomer.data.model.qclistmodel


import com.google.gson.annotations.SerializedName

data class QCRequest(
    @SerializedName("imei")
    val imei: String,
    @SerializedName("token")
    val token: String
)