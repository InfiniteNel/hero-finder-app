package com.jroslar.heroapp.ui.forgotpassword

data class ForgotPasswordState (
    val isLoading: Boolean = false,
    val isValidEmail: Boolean = true,
    val isError: Boolean = false,
    val isSuccess: Boolean = false
)