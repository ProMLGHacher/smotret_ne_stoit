package com.krea.kollege.feauture.room.model

import com.krea.kollege.domain.model.Device

data class RoomState(
    val name: String,
    val devices: List<Device>
)
