package com.jroslar.heroapp.ui.signup

data class SignupState (
    val isLoading: Boolean = false,
    val isValidEmail: Boolean = true,
    val isValidUsername: Boolean = true,
    val isValidPassword: Boolean = true,
    val isValidRepeatPassword: Boolean = true,
    val isError: Boolean = false,
    val isErrorDuplicateUser: Boolean = false,
    val isErrorNetwork: Boolean = false,
    val isSuccess: Boolean = false,
) {
    fun userValidated() = isValidEmail && isValidUsername && isValidPassword && isValidRepeatPassword
}