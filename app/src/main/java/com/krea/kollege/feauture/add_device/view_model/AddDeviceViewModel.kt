package com.krea.kollege.feauture.add_device.view_model

import android.util.Patterns
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.krea.kollege.domain.repository.RoomRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddDeviceViewModel @Inject constructor(
    private val repository: RoomRepository
) : ViewModel() {

    private val _state = mutableStateOf("")
    val state = ::_state

    fun add(name: String) {
        repository.addDevice(name)
    }

}