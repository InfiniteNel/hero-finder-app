package com.jroslar.heroapp.domain.usecase

import com.google.firebase.auth.FirebaseUser
import com.jroslar.heroapp.data.network.firebase.AuthenticationService
import javax.inject.Inject

class GetUserUseCase @Inject constructor(private val authenticationService: AuthenticationService) {
    operator fun invoke(): FirebaseUser? =
        authenticationService.getCurrentUser()
}