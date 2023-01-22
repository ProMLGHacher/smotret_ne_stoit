package com.krea.kollege.feauture.main.home.view_model

import android.content.SharedPreferences
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.maps.model.LatLng
import com.krea.kollege.domain.model.Geocode
import com.krea.kollege.domain.model.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddressViewModel @Inject constructor(
    private val sharedPreferences: SharedPreferences,
    httpClient: HttpClient
) : ViewModel() {

    private val _state = mutableStateOf("")
    val state = ::_state

    init {
        val a = sharedPreferences.getString("ADDR", "")
        val ok = a?.split(" ")
        ok?.forEach {
           Log.e("sss", it)
        }
        val k = LatLng(ok?.get(0)?.toDouble() ?: 0.0, ok?.get(0)?.toDouble() ?: 0.0)
        viewModelScope.launch {
            val a : String = httpClient.get {
                parameter("geocode", "${ok?.get(0)?.toDouble() ?: 0.0},${ok?.get(0)?.toDouble() ?: 0.0}")
            }.body()
            Log.e("DDD", a.toString())
            //_state.value = a.response.GeoObjectCollection.featureMember.firstOrNull()?.GeoObject?.metaDataProperty?.GeocoderMetaData?.text ?: "ошибка"
        }
    }
}