package com.krea.kollege.data.repository

import android.content.SharedPreferences
import com.krea.kollege.domain.model.Room
import com.krea.kollege.domain.repository.RoomRepository

class RoomRepositoryImpl(
    private val sharedPreferences: SharedPreferences
) : RoomRepository {

    private var list = mutableListOf<Room>()

    override fun get(): List<Room> {
        return list
    }

    override fun add(room: Room) {
        list.add(room)
    }

    override fun getCurrentRoom(): String {
        return sharedPreferences.getString("CURRENT_ROOM", "")!!
    }

    override fun setCurrentRoom(name: String) {
        sharedPreferences.edit().putString("CURRENT_ROOM", name).apply()
    }


}