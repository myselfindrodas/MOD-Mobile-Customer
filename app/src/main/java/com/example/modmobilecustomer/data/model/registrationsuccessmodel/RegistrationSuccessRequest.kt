package com.example.modmobilecustomer.data.model.registrationsuccessmodel


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class RegistrationSuccessRequest(
    @SerializedName("recipients")
    val recipients: List<Recipient>,
    @SerializedName("short_url")
    val shortUrl: String,
    @SerializedName("template_id")
    val templateId: String
):Serializable