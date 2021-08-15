package com.wse.project.networkdb

import android.net.ParseException
import android.util.Log
import com.google.gson.JsonIOException
import com.google.gson.JsonParseException
import com.wse.project.R
import retrofit2.HttpException
import java.net.ConnectException
import java.net.UnknownHostException

class PassengerRepository(private val mApiService: ApiService) {
    suspend fun getPassenger(page:Int?) = mApiService.getPassenger(page)
    companion object {
        private val TAG = PassengerRepository::class.java.simpleName
        fun getErrorMessage(error: Throwable): Int {
            val stringId: Int
            Log.e(TAG, "getErrorMessage: $error")
            when (error) {
                is UnknownHostException -> {
                    stringId =  R.string.unknown_host_exception
                }
                is ConnectException -> {
                    stringId = R.string.your_in_offline
                }
                is ParseException, is JsonIOException, is JsonParseException -> {
                    stringId = R.string.parser_exception
                }
                is HttpException -> {
                    stringId = R.string.un_caught_exception
                }
                else -> {
                    stringId = R.string.un_caught_exception
                }

            }
            return stringId
        }
    }
}