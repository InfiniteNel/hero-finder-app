package com.jroslar.heroapp.domain.usecase

import com.jroslar.heroapp.data.network.firebase.AuthenticationService
import javax.inject.Inject

class SendEmailResetPasswordUseCase @Inject constructor(private val authenticationService: AuthenticationService) {

    suspend operator fun invoke(email: String): Boolean {
        return authenticationService.sendEmailResetPassword(email)
    }
}