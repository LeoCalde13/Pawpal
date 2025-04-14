package com.caldeira.pawpal.ui.viewmodels

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.caldeira.pawpal.EMPTY_STRING
import com.caldeira.pawpal.model.CatDetails
import com.caldeira.pawpal.repository.CatsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "CatsBreedsViewModel"

@HiltViewModel
class CatsBreedsViewModel @Inject constructor(
    private val catsRepository: CatsRepository,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.IO,
) : BaseViewModel(catsRepository) {
    private val _breedsListState = MutableStateFlow(emptyList<CatDetails>())
    val breedsListState = _breedsListState.asStateFlow()

    val searchState = mutableStateOf(EMPTY_STRING)

    init {
        fetchCatBreeds()
    }

    fun fetchCatBreeds() {
        Log.d(TAG, "fetching")
        viewModelScope.launch(defaultDispatcher) {
            _breedsListState.value = catsRepository.getCatBreeds().filtered()
        }
    }

    fun searchBreed(query: String) {
        searchState.value = query
        viewModelScope.launch {
            _breedsListState.value = catsRepository.getCatBreeds().filtered()

        }
    }

    override fun setBreedIsFavorite(id: String, isFavorite: Boolean) {
        super.setBreedIsFavorite(id, isFavorite)
        _breedsListState.value = _breedsListState.value.map {
            if (it.id == id) it.copy(isFavorite = isFavorite)
            else it
        }
    }

    private fun List<CatDetails>.filtered() =
        filter { it.name.contains(searchState.value, ignoreCase = true) }

}