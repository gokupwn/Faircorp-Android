package com.faircorp.apiservices


import com.faircorp.model.WindowDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface WindowApiService {
    @GET("windows")
    fun findAll(): Call<List<WindowDto>>

    @GET("windows/{id}")
    fun findById(@Path("id") id: Long): Call<WindowDto>

    @GET("windows/rooms/{roomId}")
    fun findWindowsByRoomId(@Path("roomId") roomId: Long?): Call<List<WindowDto>>

    @PUT("windows/{id}/switch")
    fun switchStatus(@Path("id") id: Long): Call<WindowDto>
}