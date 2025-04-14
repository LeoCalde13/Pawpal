package com.caldeira.pawpal.ui.viewmodels

import androidx.annotation.CallSuper
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.caldeira.pawpal.repository.CatsRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

abstract class BaseViewModel(
    private val catsRepository: CatsRepository,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.IO,
) : ViewModel() {

    @CallSuper
    open fun setBreedIsFavorite(id: String, isFavorite: Boolean) {
        viewModelScope.launch(defaultDispatcher) {
            catsRepository.setBreedFavoriteState(id, isFavorite)
        }
    }
}