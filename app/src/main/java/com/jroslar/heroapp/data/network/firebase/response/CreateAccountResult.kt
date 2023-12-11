package com.jroslar.heroapp.data.network.firebase.response

sealed class CreateAccountResult {
    object ErrorDuplicateUser: CreateAccountResult()
    object ErrorNetwork: CreateAccountResult()
    object Error: CreateAccountResult()
    object Success: CreateAccountResult()
}