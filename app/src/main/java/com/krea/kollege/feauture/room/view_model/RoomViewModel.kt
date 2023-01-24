package com.krea.kollege.feauture.room.view_model

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.krea.kollege.domain.repository.RoomRepository
import com.krea.kollege.feauture.room.model.RoomState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RoomViewModel @Inject constructor(
    roomRepository: RoomRepository
) : ViewModel() {

    private val _state = mutableStateOf(RoomState(
        name = roomRepository.getCurrentRoom()
    ))
    val state = ::_state



}