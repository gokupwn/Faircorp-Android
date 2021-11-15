package com.faircorp.apiservices

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class ApiServices {
    private val baseUrl = "https://faircorp-hassan-alachek.cleverapps.io"
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

    val buildingApiService: BuildingApiService by lazy {
        Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl("$baseUrl/api/")
            .build()
            .create(BuildingApiService::class.java)
    }
}