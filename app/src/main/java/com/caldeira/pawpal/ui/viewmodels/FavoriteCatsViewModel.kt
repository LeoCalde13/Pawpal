package com.caldeira.pawpal.ui.viewmodels

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

@HiltViewModel
class FavoriteCatsViewModel @Inject constructor(
    private val catsRepository: CatsRepository,
    defaultDispatcher: CoroutineDispatcher = Dispatchers.Default,
) : BaseViewModel(catsRepository) {

    private val _favoriteCatsListState = MutableStateFlow(emptyList<CatDetails>())
    val favoriteCatsListState = _favoriteCatsListState.asStateFlow()

    init {
        viewModelScope.launch(defaultDispatcher) {
            _favoriteCatsListState.value = catsRepository.getCatBreeds().filter { it.isFavorite }
        }
    }

    override fun setBreedIsFavorite(id: String, isFavorite: Boolean) {
        super.setBreedIsFavorite(id, isFavorite)
        _favoriteCatsListState.value = _favoriteCatsListState.value.mapNotNull {
            if (it.id == id && !isFavorite) null
            else it
        }
    }
}