package com.faircorp.model

enum class HeaterStatus {ON, OFF}

data class HeaterDto (val id: Long, val name: String, val power: Long, val roomName: String, val roomId: Long, val heaterStatus: HeaterStatus )