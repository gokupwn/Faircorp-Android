package com.faircorp.apiservices

import com.faircorp.model.HeaterDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface HeaterApiService {
    @GET("heaters")
    fun findAll(): Call<List<HeaterDto>>

    @GET("heaters/{id}")
    fun findById(@Path("id") id: Long?): Call<HeaterDto>
}