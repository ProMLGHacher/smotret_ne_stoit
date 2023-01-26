package com.krea.kollege.data.repository

import android.content.SharedPreferences
import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.krea.kollege.domain.model.Device
import com.krea.kollege.domain.model.Room
import com.krea.kollege.domain.repository.RoomRepository
import io.ktor.util.reflect.*
import kotlin.reflect.javaType
import kotlin.reflect.typeOf


//class RoomRepositoryImpl(
//    private val sharedPreferences: SharedPreferences
//) : RoomRepository {
//
//    private var list = mutableListOf<Room>()
//
//    var builder: GsonBuilder = GsonBuilder()
//    var gson: Gson = builder.create()
//    val sType = object : TypeToken<List<Room>>() { }.type
//
//    override fun get(): List<Room> {
//        Log.e("sss",
//            sharedPreferences.getString("all", gson.toJson(list))!!
//        )
//        Log.e("sss",
//            gson.fromJson<List<Room>>(sharedPreferences.getString("all", gson.toJson(list))!!, sType)
//                .toString()
//        )
//        return list
//    }
//
//    override fun add(room: Room) {
//        val list = gson.fromJson<MutableList<Room>>(sharedPreferences.getString("all", gson.toJson(list))!!, sType)
//        list.add(room)
//        sharedPreferences.edit().putString("all", gson.toJson(list)).apply()
//    }
//
//    override fun getCurrentRoom(): String {
//        return sharedPreferences.getString("CURRENT_ROOM", "")!!
//    }
//
//    override fun setCurrentRoom(name: String) {
//        sharedPreferences.edit().putString("CURRENT_ROOM", name).apply()
//    }
//
//    override fun switchDeviceActivity(name: String) {
//        list.all {
//            if (it.name == getCurrentRoom()) {
//                it.listOfDevices.filter { ok -> ok.name == name }.apply {
//                    first().isActive = !first().isActive
//                }
//                return@all true
//            }
//            else {
//                return@all true
//            }
//        }
//    }
//
//    override fun addDevice(name: String) {
//        list.all {
//            if (it.name == getCurrentRoom()) {
//                it.listOfDevices.add(Device(name = name, false))
//                return@all true
//            }
//            else {
//                return@all true
//            }
//        }
//        Log.e("LLL", list.toString())
//    }
//
//}

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