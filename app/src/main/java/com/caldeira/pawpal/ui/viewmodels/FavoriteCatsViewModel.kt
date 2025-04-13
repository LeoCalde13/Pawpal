package com.caldeira.pawpal.ui.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.caldeira.pawpal.model.CatDetails
import com.caldeira.pawpal.repository.CatsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "FavoriteCatsViewModel"

@HiltViewModel
class FavoriteCatsViewModel @Inject constructor(
    private val catsRepository: CatsRepository,
    defaultDispatcher: CoroutineDispatcher = Dispatchers.IO,
) : ViewModel() {

    private val _favoriteCatsListState = MutableStateFlow(emptyList<CatDetails>())
    val favoriteCatsListState = _favoriteCatsListState.asStateFlow()

    init {
        viewModelScope.launch(defaultDispatcher) {
            _favoriteCatsListState.value = catsRepository.getCatBreeds().filter { it.isFavorite }
        }
    }

    fun setBreedIsFavorite(id: String, favorite: Boolean) {
        catsRepository.setBreedFavoriteState(id, favorite)
        Log.d(TAG, "before=${_favoriteCatsListState.value}")
        _favoriteCatsListState.value = _favoriteCatsListState.value.mapNotNull {
            if (it.id == id && !favorite) null
            else it
        }
    }
}