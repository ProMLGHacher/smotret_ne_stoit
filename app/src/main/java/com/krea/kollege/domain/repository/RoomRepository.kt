package com.krea.kollege.domain.repository

import com.krea.kollege.domain.model.Room

interface RoomRepository {
    fun get() : List<Room>
    fun add(room: Room)
    fun getCurrentRoom() : String
    fun setCurrentRoom(name: String)
}