package com.krea.kollege.feauture.auth.model

sealed class AuthState{
    data class Auth(
        val isLogin: Boolean = true,
        val mail: String = "",
        val password: String = "",
        val name: String = "",
        val error: String? = null
    ) : AuthState()
    object Success : AuthState()
}
