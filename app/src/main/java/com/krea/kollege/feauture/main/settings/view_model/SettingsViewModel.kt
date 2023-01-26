package com.krea.kollege.feauture.main.settings.view_model

import android.content.SharedPreferences
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : ViewModel() {

    val name = mutableStateOf(sharedPreferences.getString("Username", "")!!)
    val email = mutableStateOf(sharedPreferences.getString("Email", "")!!)
    val phone = mutableStateOf(sharedPreferences.getString("Phone", "")!!)
    val gender = mutableStateOf(sharedPreferences.getString("Gender", "")!!)

    fun setName(value: String) {
        name.value = value
    }

    fun setEmail(value: String) {
        email.value = value
    }

    fun setPhone(value: String) {
        phone.value = value
    }

    fun setGender(value: String) {
        gender.value = value
    }

    fun out() {
        sharedPreferences.edit().putBoolean("isLogin", false).apply()
    }

    fun submit() {
        sharedPreferences.edit().putString("Username", name.value).apply()
        sharedPreferences.edit().putString("Email", email.value).apply()
        sharedPreferences.edit().putString("Phone", phone.value).apply()
        sharedPreferences.edit().putString("Gender", gender.value).apply()
    }

}