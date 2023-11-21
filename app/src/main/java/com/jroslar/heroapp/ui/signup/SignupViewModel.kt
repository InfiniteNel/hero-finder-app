package com.jroslar.heroapp.ui.signup

import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jroslar.heroapp.core.Constant.MIN_PASSWORD_LENGTH
import com.jroslar.heroapp.core.Constant.MIN_USERNAME_LENGTH
import com.jroslar.heroapp.data.network.firebase.response.CreateAccountResult
import com.jroslar.heroapp.domain.usecase.CreateAccountUseCase
import com.jroslar.heroapp.ui.signup.model.UserSignupData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(
    private val createAccountUseCase: CreateAccountUseCase
): ViewModel() {

    private var _state = MutableStateFlow(SignupState())
    val state: StateFlow<SignupState> = _state

    fun onClickSignup(userSignupData: UserSignupData) {
        onChangeText(userSignupData)

        if (state.value.userValidated()) {
            createAccount(userSignupData)
        }
    }

    fun onChangeText(userSignupData: UserSignupData) {
        _state.value = SignupState(
            isValidEmail = isValidEmail(userSignupData.email),
            isValidUsername = isValidUsername(userSignupData.userName),
            isValidPassword = isValidPassword(userSignupData.password),
            isValidRepeatPassword = isValidRepeatPassword(userSignupData.password, userSignupData.repeatPassword)
        )
    }

    private fun createAccount(userSignupData: UserSignupData) {
        viewModelScope.launch {
            _state.value = SignupState(isLoading = true)
            when (withContext(Dispatchers.IO) { createAccountUseCase(userSignupData) }) {
                CreateAccountResult.ErrorDuplicateUser -> _state.value = SignupState(isError = true)
                CreateAccountResult.Error -> _state.value = SignupState(isError = true)
                CreateAccountResult.Success -> _state.value = SignupState(isSuccess = true)
            }
            _state.value = SignupState(isLoading = false, isError = false, isSuccess = false)
        }
    }

    private fun isValidEmail(email: String) =
        Patterns.EMAIL_ADDRESS.matcher(email).matches() && email.isNotEmpty()

    private fun isValidPassword(password: String): Boolean =
        password.length >= MIN_PASSWORD_LENGTH && password.isNotEmpty()

    private fun isValidRepeatPassword(password: String, repeatPassword: String): Boolean =
        password == repeatPassword

    private fun isValidUsername(name: String): Boolean =
        name.length >= MIN_USERNAME_LENGTH && name.isNotEmpty()
}