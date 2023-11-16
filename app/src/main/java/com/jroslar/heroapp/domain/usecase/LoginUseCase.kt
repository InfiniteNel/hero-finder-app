package com.jroslar.heroapp.domain.usecase

import com.jroslar.heroapp.data.network.firebase.AuthenticationService
import com.jroslar.heroapp.data.network.firebase.response.LoginResult
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val authenticationService: AuthenticationService) {

    suspend operator fun invoke(email: String, password: String): LoginResult =
        authenticationService.login(email, password)
}