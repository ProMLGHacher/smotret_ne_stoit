package com.krea.kollege.feauture.main.home.view_model

import android.content.SharedPreferences
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.krea.kollege.domain.model.geocode.Geocode
import dagger.hilt.android.lifecycle.HiltViewModel
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class AddressViewModel @Inject constructor(
    private val sharedPreferences: SharedPreferences,
    private val httpClient: HttpClient
) : ViewModel() {

    private val _state = mutableStateOf("")
    val state = ::_state

    init {
        update()
    }

    fun update() {
        val a = sharedPreferences.getString("ADDR", "0.0 0.0")!!.split(" ")
        viewModelScope.launch {
            withContext(Dispatchers.Main) {
                try {
                    val a : Geocode = httpClient.get {
                        parameter("geocode", "${a[1].toDouble()},${a[0].toDouble()}")
                    }.body()
                    _state.value = a.response.GeoObjectCollection.featureMember[0].GeoObject.metaDataProperty.GeocoderMetaData.text
                } catch (e: java.lang.Exception) {
                    _state.value = "На данной точке нет объектов у этого яндекс геокодераааааааааа"
                }
            }
        }
    }
}