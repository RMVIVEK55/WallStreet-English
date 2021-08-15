package com.wse.project.utils

import com.wse.project.networkdb.ApiService
import com.wse.project.networkdb.PassengerRepository

object InitializationObject {
    private fun provideRepository() = ApiService.create()

    fun provideViewModelFactory() = ViewModelFactory(provideRepository())
}