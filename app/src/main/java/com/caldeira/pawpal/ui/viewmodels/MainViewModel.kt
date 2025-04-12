package com.caldeira.pawpal.ui.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

import androidx.lifecycle.viewModelScope
import com.caldeira.pawpal.model.CatDetails
import com.caldeira.pawpal.repository.CatsRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(
    private val catsRepository: CatsRepository = CatsRepository(),
    defaultDispatcher: CoroutineDispatcher = Dispatchers.IO,
) : ViewModel() {

    val breedsListState = mutableStateOf(emptyList<CatDetails>())

    init {
        viewModelScope.launch(defaultDispatcher) {
            breedsListState.value = catsRepository.getCatBreeds()
        }
    }
}