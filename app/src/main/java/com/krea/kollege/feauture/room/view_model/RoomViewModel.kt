package com.krea.kollege.feauture.room.view_model

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.krea.kollege.domain.repository.RoomRepository
import com.krea.kollege.feauture.room.model.RoomState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class RoomViewModel @Inject constructor(
    private val roomRepository: RoomRepository
) : ViewModel() {

    val _state = MutableStateFlow(RoomState(
        name = roomRepository.getCurrentRoom(),
        devices = emptyList()
    ))
    val state = _state

    val selected = mutableStateOf("")

    init {
        update()
    }

    fun switch() {
        roomRepository.switchDeviceActivity(selected.value)
    }

    fun update() {
        roomRepository.get().forEach {
            if (it.name == _state.value.name) {
                Log.e("qwqwqw", _state.value.toString())
                viewModelScope.launch {
                    withContext(Dispatchers.Main) {
                        _state.emit(RoomState(
                            name = _state.value.name,
                            devices = it.listOfDevices
                        ))
                        if (selected.value == "") {
                            selected.value = it.listOfDevices.firstOrNull()?.name ?: ""
                        } else {
                            selected.value = selected.value
                        }
                    }
                }
                return@forEach
            }
        }
        Log.e("qwqwqw", _state.value.toString())
    }

}