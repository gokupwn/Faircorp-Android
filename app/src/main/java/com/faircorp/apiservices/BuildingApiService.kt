package com.faircorp.apiservices

import com.faircorp.model.BuildingDto
import retrofit2.Call
import retrofit2.http.GET

interface BuildingApiService {
    @GET("buildings")
    fun findAll(): Call<List<BuildingDto>>
}