package com.jroslar.heroapp.ui.login

data class LoginState (
    val isLoading: Boolean = false,
    val isValidEmail: Boolean = true,
    val isValidPassword: Boolean = true,
    val isError: Boolean = false,
    val isSuccess: Boolean = false,
)