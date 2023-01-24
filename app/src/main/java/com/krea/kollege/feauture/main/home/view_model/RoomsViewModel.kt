package com.krea.kollege.feauture.main.home.view_model

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.krea.kollege.domain.model.Room
import com.krea.kollege.domain.repository.RoomRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RoomsViewModel @Inject constructor(
    private val roomRepository: RoomRepository
) : ViewModel() {

    var state = mutableStateOf<List<Room>>(emptyList())
        private set

    init {
        getRooms()
    }

    fun getRooms() {
        state.value = roomRepository.get()
    }

    fun set(name: String) {
        roomRepository.setCurrentRoom(name)
    }

    fun get(): String {
        return roomRepository.getCurrentRoom()
    }

}