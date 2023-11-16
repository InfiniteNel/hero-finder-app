package com.jroslar.heroapp.ui.login

import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jroslar.heroapp.core.Constant.MIN_PASSWORD_LENGTH
import com.jroslar.heroapp.data.network.firebase.response.LoginResult
import com.jroslar.heroapp.domain.usecase.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val loginUseCase: LoginUseCase): ViewModel() {

    private var _state = MutableStateFlow(LoginState())
    val state: StateFlow<LoginState> = _state

    fun onChangeText(email: String, password: String) {
        _state.value = LoginState(
            isValidEmail = isValidEmail(email),
            isValidPassword = isValidPassword(password)
        )
    }

    fun onLoginClick(email: String, password: String) {
        if (isValidEmail(email) && isValidPassword(password)) {
            loginUser(email, password)
        } else {
            onChangeText(email, password)
        }
    }

    private fun loginUser(email: String, password: String) {
        viewModelScope.launch {
            _state.value = LoginState(isLoading = true)
            when (withContext(Dispatchers.IO) { loginUseCase(email, password) }) {
                LoginResult.Error -> {
                    _state.value = LoginState(isError = true)
                }
                is LoginResult.Success -> {
                    _state.value = LoginState(isSuccess = true)
                }
            }
            _state.value = LoginState(isLoading = false, isError = false, isSuccess = false)
        }
    }

    private fun isValidEmail(email: String) =
        Patterns.EMAIL_ADDRESS.matcher(email).matches() && email.isNotEmpty()

    private fun isValidPassword(password: String): Boolean =
        password.length >= MIN_PASSWORD_LENGTH && password.isNotEmpty()
}