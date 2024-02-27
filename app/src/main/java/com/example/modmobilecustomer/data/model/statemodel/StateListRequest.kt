package com.example.modmobilecustomer.data.model.statemodel

import com.google.gson.annotations.SerializedName

data class StateListRequest(
    @SerializedName("state")
    val state: String,
    @SerializedName("token")
    val token: String,
)