package com.jroslar.heroapp.ui.signup

import android.util.Log
import android.util.Patterns
import androidx.lifecycle.ViewModel
import com.jroslar.heroapp.core.Constant.MIN_PASSWORD_LENGTH
import com.jroslar.heroapp.core.Constant.MIN_USERNAME_LENGTH
import com.jroslar.heroapp.ui.signup.model.UserSignupData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(): ViewModel() {

    private var _state = MutableStateFlow(SignupState())
    val state: StateFlow<SignupState> = _state

    fun onClickSignup(userSignupData: UserSignupData) {
        onChangeText(userSignupData)

        if (state.value.userValidated()) {
            Log.d("HeroFinder", "Create Account.")
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

    private fun isValidEmail(email: String) =
        Patterns.EMAIL_ADDRESS.matcher(email).matches() && email.isNotEmpty()

    private fun isValidPassword(password: String): Boolean =
        password.length >= MIN_PASSWORD_LENGTH && password.isNotEmpty()

    private fun isValidRepeatPassword(password: String, repeatPassword: String): Boolean =
        password == repeatPassword

    private fun isValidUsername(name: String): Boolean =
        name.length >= MIN_USERNAME_LENGTH && name.isNotEmpty()
}