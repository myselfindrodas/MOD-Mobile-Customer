package com.example.modmobilecustomer.data.model.addtocartmodel


import com.google.gson.annotations.SerializedName

data class AddtoCartRequest(
    @SerializedName("cartJSON")
    val cartJSON: ArrayList<AddtoCartItem>,
    @SerializedName("token")
    val token: String
)