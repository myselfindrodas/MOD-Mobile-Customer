package com.example.modmobilecustomer.data.model.reviewlistmodel


import com.google.gson.annotations.SerializedName

data class ReviewlistRequest(
    @SerializedName("productid")
    val productid: String,
    @SerializedName("token")
    val token: String,
    @SerializedName("userid")
    val userid: String
)