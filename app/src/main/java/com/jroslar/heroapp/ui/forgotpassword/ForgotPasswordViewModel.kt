package com.jroslar.heroapp.ui.forgotpassword

import android.util.Log
import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jroslar.heroapp.domain.usecase.SendEmailResetPasswordUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ForgotPasswordViewModel @Inject constructor(
    private val sendEmailResetPasswordUseCase: SendEmailResetPasswordUseCase
) : ViewModel() {

    private var _state = MutableStateFlow(ForgotPasswordState())
    val state: StateFlow<ForgotPasswordState> = _state

    fun onClickForgotPassword(email: String) {
        onChangeText(email)

        if (state.value.isValidEmail) {
            sendEmailResetPassword(email)
        }
    }

    fun onChangeText(email: String) {
        _state.value = ForgotPasswordState(
            isValidEmail = isValidEmail(email)
        )
    }

    private fun sendEmailResetPassword(email: String) {
        viewModelScope.launch {
            _state.value = ForgotPasswordState(isLoading = true)

            if (withContext(Dispatchers.IO) { sendEmailResetPasswordUseCase(email) }) {
                _state.value = ForgotPasswordState(isSuccess = true)
                Log.d("HeroFinder", "SUCCESS")
            } else {
                _state.value = ForgotPasswordState(isError = true)
                Log.d("HeroFinder", "ERROR")
            }

            _state.value = ForgotPasswordState(isLoading = false, isError = false, isSuccess = false)
        }
    }

    private fun isValidEmail(email: String) =
        Patterns.EMAIL_ADDRESS.matcher(email).matches() && email.isNotEmpty()
}