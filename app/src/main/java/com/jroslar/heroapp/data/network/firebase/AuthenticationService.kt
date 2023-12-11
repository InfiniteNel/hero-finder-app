package com.jroslar.heroapp.data.network.firebase

import com.google.firebase.FirebaseException
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.jroslar.heroapp.data.network.firebase.response.CreateAccountResult
import com.jroslar.heroapp.data.network.firebase.response.LoginResult
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthenticationService @Inject constructor(
    private val firebase: FirebaseAuth
) {
    suspend fun login(email: String, password: String): LoginResult = runCatching {
        firebase.signInWithEmailAndPassword(email, password).await()
    }.toLoginResult()

    suspend fun createAccount(email: String, username: String, password: String): CreateAccountResult {
        return try {
            firebase.createUserWithEmailAndPassword(email, password).await()

            val profileUpdates = userProfileChangeRequest {
                displayName = username
            }

            getCurrentUser()?.updateProfile(profileUpdates)?.await()

            CreateAccountResult.Success
        } catch(e: FirebaseAuthUserCollisionException) {
            CreateAccountResult.ErrorDuplicateUser
        } catch (e: FirebaseException) {
            CreateAccountResult.Error
        }
    }

    fun signOut(): Boolean {
        return try {
            firebase.signOut()
            true
        } catch (e: FirebaseException) {
            false
        }
    }

    suspend fun sendEmailResetPassword(email: String): Boolean {
        return try {
            firebase.sendPasswordResetEmail(email).await()
            true
        } catch (e: FirebaseException) {
            false
        }
    }

    fun getCurrentUser(): FirebaseUser? {
        return firebase.currentUser
    }

    private fun Result<AuthResult>.toLoginResult() = when (getOrNull()) {
        null -> LoginResult.Error
        else -> LoginResult.Success
    }
}