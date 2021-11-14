package com.faircorp.apiservices

import com.faircorp.model.RoomDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RoomApiService {

    @GET("rooms")
    fun findAll(): Call<List<RoomDto>>

    @GET("rooms/{id}")
    fun findById(@Path("id") id: Long?): Call<RoomDto>

    @GET("rooms/buildings/{buildingId}")
    fun findRoomsByBuildingId(@Path("buildingId") buildinId: Long): Call<List<RoomDto>>
}