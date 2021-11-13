package com.faircorp.model

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class ApiServices {
    val baseUrl = "http://27a0-5-51-136-178.ngrok.io"
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
}