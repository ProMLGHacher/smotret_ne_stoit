package com.krea.kollege.feauture.add_room.view_model

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.krea.kollege.domain.model.Room
import com.krea.kollege.domain.model.RoomType
import com.krea.kollege.domain.repository.RoomRepository
import com.krea.kollege.feauture.add_room.model.AddRoomState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddRoomViewModel @Inject constructor(
    private val roomRepository: RoomRepository
) : ViewModel() {

    var state = mutableStateOf(AddRoomState())
        private set

    fun setName(name: String) {
        state.value = state.value.copy(
            name = name
        )
    }

    fun setState(type: RoomType) {
        state.value = state.value.copy(
            type = type
        )
    }

    fun save() : Boolean {
        if (state.value.name == "") {
            return false
        }
        roomRepository.get().forEach {
            if(it.name == state.value.name) {
                return false
            }
        }
        roomRepository.add(
            Room(
                name = state.value.name,
                type = state.value.type,
            )
        )
        return true
    }

}