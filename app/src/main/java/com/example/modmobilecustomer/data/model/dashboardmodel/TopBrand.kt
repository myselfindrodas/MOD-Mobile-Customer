package com.example.modmobilecustomer.data.model.dashboardmodel


import com.google.gson.annotations.SerializedName

data class TopBrand(
    @SerializedName("brand_id")
    val brandId: String,
    @SerializedName("img_url")
    val imgUrl: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("status")
    val status: String
)