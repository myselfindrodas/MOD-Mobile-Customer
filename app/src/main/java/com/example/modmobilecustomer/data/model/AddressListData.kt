package com.example.modmobilecustomer.data.model

data class AddressListData(
    val imageOfAddress: Int,
    val tvAddressType: String,
    val address: String,
    var isSelected: Boolean= false
)
