package com.krea.kollege.feauture.map.view_model

import android.content.SharedPreferences
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : ViewModel() {

    private val a = sharedPreferences.getString("ADDR", "0.0 0.0")!!.split(" ")
    val b = mutableStateOf(LatLng(a[0].toDouble(), a[1].toDouble()))

    fun setCoordinates(value: LatLng) {
        sharedPreferences.edit().putString("ADDR", "${value.latitude} ${value.longitude}").apply()
    }
}