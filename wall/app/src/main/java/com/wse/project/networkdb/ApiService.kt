package com.wse.project.networkdb

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {


    @GET("passenger")
    suspend fun getPassenger(@Query("page") page: Int? ,@Query("size") size: Int=10): PassengersModel


    companion object {
        private const val BASE_URL = "https://api.instantwebtools.net/v1/"

        fun create(): ApiService {
            val logger = HttpLoggingInterceptor()

                logger.level = HttpLoggingInterceptor.Level.BODY


            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService::class.java)
        }
    }
}