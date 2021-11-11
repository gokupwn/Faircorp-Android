package com.faircorp.services

import com.faircorp.model.Status
import com.faircorp.model.WindowDto

class WindowService {

    companion object {


        // Fake lights
        val WINDOWS: List<WindowDto> = listOf(
            WindowDto(1, "Entry Window", "Room EF 6.10", 10, Status.CLOSED),
            WindowDto(2, "Back Window", "Room EF 6.07", 11, Status.CLOSED),
            WindowDto(3, "Sliding door", "Room EF 5.01", 12,Status.OPEN),
        )
    }

    fun findById(id: Long) = WINDOWS.firstOrNull { it.id == id}

    fun findAll() = WINDOWS.sortedBy { it.name }
}