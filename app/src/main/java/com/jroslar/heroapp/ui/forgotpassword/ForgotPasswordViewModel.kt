package com.jroslar.heroapp.ui.forgotpassword

import android.util.Patterns
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class ForgotPasswordViewModel @Inject constructor() : ViewModel() {

    private var _state = MutableStateFlow(ForgotPasswordState())
    val state: StateFlow<ForgotPasswordState> = _state

    fun onChangeText(email: String) {
        _state.value = ForgotPasswordState(
            isValidEmail = isValidEmail(email)
        )
    }

    private fun isValidEmail(email: String) =
        Patterns.EMAIL_ADDRESS.matcher(email).matches() && email.isNotEmpty()
}