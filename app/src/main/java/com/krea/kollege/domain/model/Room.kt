package com.krea.kollege.domain.model

import com.krea.kollege.R

sealed class RoomType(
    val icon: Int,
    val name: String
) {
    object Kitchen : RoomType(R.drawable.kitchen, name = "Kitchen")
    object Bedroom : RoomType(R.drawable.bedroom, name = "Bedroom")
    object Bathroom : RoomType(R.drawable.bathroom, name = "Bathroom")
    object Office : RoomType(R.drawable.office, name = "Office")
    object TvRoom : RoomType(R.drawable.tv_room, name = "TV room")
    object LivingRoom : RoomType(R.drawable.living_room, name = "Living room")
    object Garage : RoomType(R.drawable.garage, name = "Garage")
    object Toilet : RoomType(R.drawable.toilet, name = "Toilet")
    object KidRoom : RoomType(R.drawable.kid_room, name = "Kid room")
}

data class Device(
    val name: String
)

data class Room(
    val name: String,
    val type: RoomType,
    val listOfDevices: List<Device> = emptyList()
)