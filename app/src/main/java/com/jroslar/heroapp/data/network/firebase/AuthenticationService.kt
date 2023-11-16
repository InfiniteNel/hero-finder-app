package com.jroslar.heroapp.data.network.firebase

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.jroslar.heroapp.data.network.firebase.response.LoginResult
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthenticationService @Inject constructor(
    private val firebase: FirebaseAuth
) {
    suspend fun login(email: String, password: String): LoginResult = runCatching {
        firebase.signInWithEmailAndPassword(email, password).await()
    }.toLoginResult()

    private fun Result<AuthResult>.toLoginResult() = when (getOrNull()) {
        null -> LoginResult.Error
        else -> LoginResult.Success
    }
}