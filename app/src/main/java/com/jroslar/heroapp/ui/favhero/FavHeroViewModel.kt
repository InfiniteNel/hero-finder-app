package com.jroslar.heroapp.ui.favhero

import androidx.lifecycle.ViewModel
import com.jroslar.heroapp.domain.usecase.GetUserUseCase
import com.jroslar.heroapp.domain.usecase.SignOutUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class FavHeroViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase,
    private val signOutUseCase: SignOutUseCase
) : ViewModel() {
    private var _state = MutableStateFlow<FavHeroState>(FavHeroState.NoData)
    val state: StateFlow<FavHeroState> = _state

    init {
        getCurrentUser()
    }

    private fun getCurrentUser() {
        val result = getUserUseCase()

        if (result == null) {
            _state.value = FavHeroState.NoData
        } else {
            _state.value = FavHeroState.Success(result.email, result.displayName)
        }
    }

    fun signOff() {
        if (signOutUseCase()) {
            _state.value = FavHeroState.SignOut
        } else {
            _state.value = FavHeroState.Error
        }
    }
}