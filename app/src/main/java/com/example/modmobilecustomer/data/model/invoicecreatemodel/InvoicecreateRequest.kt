package com.example.modmobilecustomer.data.model.invoicecreatemodel


import com.google.gson.annotations.SerializedName

data class InvoicecreateRequest(
    @SerializedName("BillJSON")
    val billJSON: BillJSON
)