package com.faircorp.model

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class ApiServices {
    val baseUrl = "http://c75e-193-49-174-63.ngrok.io"
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