package com.example.modmobilecustomer.data.model.defaultaddressmodel


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("response")
    val response: String,
    @SerializedName("type")
    val type: String
)