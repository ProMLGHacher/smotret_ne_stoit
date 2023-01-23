package com.krea.kollege.data.repository

import com.krea.kollege.domain.model.Room
import com.krea.kollege.domain.repository.RoomRepository

class RoomRepositoryImpl : RoomRepository {

    private var list = mutableListOf<Room>()

    override fun get(): List<Room> {
        return list
    }

    override fun add(room: Room) {
        list.add(room)
    }
}