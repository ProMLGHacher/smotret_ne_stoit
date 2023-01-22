package com.krea.kollege.feauture.map.view_model

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : ViewModel() {
    fun setCoordinates(value: LatLng) {
        sharedPreferences.edit().putString("ADDR", "${value.latitude} ${value.longitude}").apply()
    }
}