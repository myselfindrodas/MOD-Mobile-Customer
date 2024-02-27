package com.example.modmobilecustomer.data.model.viewcartmodel


import com.google.gson.annotations.SerializedName

data class ViewCartRequest(
    @SerializedName("token")
    val token: String,
    @SerializedName("userid")
    val userid: String
)