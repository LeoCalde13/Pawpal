package com.caldeira.pawpal.ui.viewmodels

import androidx.lifecycle.SavedStateHandle
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

@HiltViewModel
class CatDetailsViewModel @Inject constructor(
    private val catsRepository: CatsRepository,
    private val savedStateHandle: SavedStateHandle,
    defaultDispatcher: CoroutineDispatcher = Dispatchers.IO,
) : ViewModel() {
    private val _breedDetails = MutableStateFlow<CatDetails?>(null)
    val breedDetails = _breedDetails.asStateFlow()

    init {
        viewModelScope.launch(defaultDispatcher) {
            val id = savedStateHandle.get<String>("catId")
            _breedDetails.value = catsRepository.getBreedDetails(id)
        }
    }
}