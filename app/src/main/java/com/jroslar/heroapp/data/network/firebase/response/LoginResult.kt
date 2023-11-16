package com.jroslar.heroapp.data.network.firebase.response


sealed class LoginResult {
    object Error : LoginResult()
    object Success : LoginResult()
}