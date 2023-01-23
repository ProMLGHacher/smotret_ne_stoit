package com.krea.kollege.feauture.add_room.model

import com.krea.kollege.domain.model.RoomType

data class AddRoomState(
    val name: String = "",
    val type: RoomType = RoomType.Bedroom
)
