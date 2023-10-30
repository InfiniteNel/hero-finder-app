package com.jroslar.heroapp.ui.searchhero

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jroslar.heroapp.domain.usecase.GetListHeroByNameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SearchHeroViewModel @Inject constructor(
    private val getListHeroByNameUseCase: GetListHeroByNameUseCase
) : ViewModel() {

    private var _state = MutableStateFlow<SearchHeroState>(SearchHeroState.NoData(emptyList()))
    val state: StateFlow<SearchHeroState> = _state

    fun getListHeroByName(heroName: String) {
        viewModelScope.launch {
            _state.value = SearchHeroState.Loading
            val result = withContext(Dispatchers.IO) { getListHeroByNameUseCase(heroName) }

            if (result == null) {
                _state.value = SearchHeroState.Error
            } else if (result.isEmpty()) {
                _state.value = SearchHeroState.NoData(emptyList())
            } else {
                _state.value = SearchHeroState.Success(result)
            }
        }
    }
}