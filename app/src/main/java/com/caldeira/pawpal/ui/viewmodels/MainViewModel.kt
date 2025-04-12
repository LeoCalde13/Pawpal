package com.caldeira.pawpal.ui.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

import androidx.lifecycle.viewModelScope
import com.caldeira.pawpal.EMPTY_STRING
import com.caldeira.pawpal.model.CatDetails
import com.caldeira.pawpal.repository.CatsRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val catsRepository: CatsRepository = CatsRepository(),
    defaultDispatcher: CoroutineDispatcher = Dispatchers.IO,
) : ViewModel() {

    private val _breedsListState = MutableStateFlow(emptyList<CatDetails>())
    val breedsListState = _breedsListState.asStateFlow()

    val searchState = mutableStateOf(EMPTY_STRING)

    init {
        viewModelScope.launch(defaultDispatcher) {
            _breedsListState.value = catsRepository.getCatBreeds()
        }
    }

    fun searchBreed(query: String) {
        searchState.value = query
        viewModelScope.launch {
            _breedsListState.value =
                catsRepository.getCatBreeds().filter { it.name.contains(searchState.value) }
        }
    }
}