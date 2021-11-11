package com.faircorp.services

import com.faircorp.model.RoomDto

class RoomService {
    companion object {
        // Fake rooms
        val ROOMS: List<RoomDto> = listOf(
            RoomDto(1, "Room EF 6.10", 1, 18.2, 20.0),
            RoomDto(2, "Room EF 4.07", 2, 18.2, 18.0),
            RoomDto(3, "Room EF 7.10", 3, 21.2, 20.0)
        )

    }

    fun findById(id: Long) = ROOMS.firstOrNull { it.id == id}
    fun findAll() = ROOMS.sortedBy { it.name }
}