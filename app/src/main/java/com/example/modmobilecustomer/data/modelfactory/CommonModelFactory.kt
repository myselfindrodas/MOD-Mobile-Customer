package com.example.modmobilecustomer.data.modelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.modmobilecustomer.data.ApiHelper
import com.example.modmobilecustomer.data.repository.MainRepository
import com.example.modmobilecustomer.viewmodel.CommonViewModel


class CommonModelFactory(private val apiHelper: ApiHelper) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        CommonViewModel(MainRepository(apiHelper)) as T

}