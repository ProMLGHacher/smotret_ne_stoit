package com.krea.kollege.feauture.auth.view_model

import android.content.SharedPreferences
import android.util.Patterns
import androidx.lifecycle.ViewModel
import com.krea.kollege.feauture.auth.model.AuthState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : ViewModel() {

    private val _state = MutableStateFlow<AuthState>(AuthState.Auth())
    val state = _state.asStateFlow()

    fun submit() {
        val state = (state.value as AuthState.Auth)
        if (!Patterns.EMAIL_ADDRESS.matcher(state.mail).matches() || state.mail.isEmpty()) {
            _state.update {
                state.copy(
                    error = "Mail is not corrected"
                )
            }
            return
        }
        if (state.password.isEmpty()) {
            _state.update {
                state.copy(
                    error = "Password can't be an empty"
                )
            }
            return
        }
        if(state.isLogin) {
            if (state.mail == "aaa@mail.ru" && state.password == "Qwert123") {
                _state.update {
                    AuthState.Success
                }
                sharedPreferences.edit().putString("Username", "Олег").apply()
                sharedPreferences.edit().putString("Email", "aaa@mail.ru").apply()
                sharedPreferences.edit().putString("Phone", "89878884054").apply()
                sharedPreferences.edit().putString("Gender", "Олег").apply()
                sharedPreferences.edit().putBoolean("isLogin", true).apply()
                return
            } else {
                _state.update {
                    state.copy(
                        error = "Incorrect email or password"
                    )
                }
                return
            }
        } else {
            if (state.name.isEmpty()) {
                _state.update {
                    state.copy(
                        error = "Password can't be an empty"
                    )
                }
                return
            }
            _state.update {
                AuthState.Success
            }
            sharedPreferences.edit().putString("Username", state.name).apply()
            sharedPreferences.edit().putString("Email", state.mail).apply()
            sharedPreferences.edit().putString("Phone", "").apply()
            sharedPreferences.edit().putString("Gender", "").apply()
            sharedPreferences.edit().putBoolean("isLogin", true).apply()
            return
        }
    }

    fun setEmail(value: String) {
        _state.update {
            (it as AuthState.Auth).copy(
                mail = value,
                error = null
            )
        }
    }

    fun setPassword(value: String) {
        _state.update {
            (it as AuthState.Auth).copy(
                password = value,
                error = null
            )
        }
    }

    fun setName(value: String) {
        _state.update {
            (it as AuthState.Auth).copy(
                name = value,
                error = null
            )
        }
    }

    fun swapLogReg() {
        _state.update {
            (it as AuthState.Auth).copy(
                isLogin = !it.isLogin
            )
        }
    }

}