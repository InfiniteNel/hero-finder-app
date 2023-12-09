package com.jroslar.heroapp.domain.usecase

import com.jroslar.heroapp.data.network.firebase.AuthenticationService
import javax.inject.Inject

class SignOutUseCase @Inject constructor(
    private val authenticationService: AuthenticationService
) {
    operator fun invoke(): Boolean =
        authenticationService.signOut()
}