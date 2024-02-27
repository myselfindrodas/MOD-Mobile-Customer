package com.example.modmobilecustomer.data.model.postreviewmodel


import com.google.gson.annotations.SerializedName

data class PostReviewRequest(
    @SerializedName("name")
    val name: String,
    @SerializedName("productid")
    val productid: String,
    @SerializedName("rate")
    val rate: String,
    @SerializedName("review")
    val review: String,
    @SerializedName("token")
    val token: String,
    @SerializedName("userid")
    val userid: String
)