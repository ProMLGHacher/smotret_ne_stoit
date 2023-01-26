package com.krea.kollege.data.repository

import android.content.SharedPreferences
import android.util.Log
import com.krea.kollege.domain.model.Device
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

    override fun switchDeviceActivity(name: String) {
        list.all {
            if (it.name == getCurrentRoom()) {
                it.listOfDevices.filter { ok -> ok.name == name }.apply {
                    first().isActive = !first().isActive
                }
                return@all true
            }
            else {
                return@all true
            }
        }
    }

    override fun addDevice(name: String) {
        list.all {
            if (it.name == getCurrentRoom()) {
                it.listOfDevices.add(Device(name = name, false))
                return@all true
            }
            else {
                return@all true
            }
        }
        Log.e("LLL", list.toString())
    }


}