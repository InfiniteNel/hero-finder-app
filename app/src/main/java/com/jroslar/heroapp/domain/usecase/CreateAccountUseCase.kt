package com.jroslar.heroapp.domain.usecase

import com.jroslar.heroapp.data.network.firebase.AuthenticationService
import com.jroslar.heroapp.data.network.firebase.response.CreateAccountResult
import com.jroslar.heroapp.ui.signup.model.UserSignupData
import javax.inject.Inject

class CreateAccountUseCase @Inject constructor(private val authenticationService: AuthenticationService) {

    suspend operator fun invoke(userSignupData: UserSignupData): CreateAccountResult =
         authenticationService.createAccount(userSignupData.email, userSignupData.password)
}