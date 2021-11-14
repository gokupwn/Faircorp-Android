package com.faircorp.apiservices

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class ApiServices {
    private val baseUrl = "http://b5bb-5-51-136-178.ngrok.io"
    val windowsApiService : WindowApiService by lazy {
        Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl("$baseUrl/api/")
            .build()
            .create(WindowApiService::class.java)
    }

    val roomApiService: RoomApiService by lazy {
        Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl("$baseUrl/api/")
            .build()
            .create(RoomApiService::class.java)
    }

    val heaterApiService: HeaterApiService by lazy {
        Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl("$baseUrl/api/")
            .build()
            .create(HeaterApiService::class.java)
    }
}