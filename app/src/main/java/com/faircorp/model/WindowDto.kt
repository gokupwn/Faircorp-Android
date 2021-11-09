package com.faircorp.model

enum class Status { OPEN, CLOSED}

data class WindowDto(val id: Long, val name: String, val roomName: String, val roomId: Long, val windowStatus: Status)