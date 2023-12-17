package com.pratwib.animaze.ui.screen.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pratwib.animaze.data.local.AnimeEntity
import com.pratwib.animaze.data.repository.AnimeRepository
import com.pratwib.animaze.util.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(private val repository: AnimeRepository) : ViewModel() {
    private val _allFavoriteAnime =
        MutableStateFlow<UIState<List<AnimeEntity>>>(UIState.Loading)
    val allFavoriteAnime = _allFavoriteAnime.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllFavoriteAnime()
                .catch { _allFavoriteAnime.value = UIState.Error(it.message.toString()) }
                .collect { _allFavoriteAnime.value = UIState.Success(it) }
        }
    }

    fun updateFavoriteAnime(id: Int, isFavorite: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateFavoriteAnime(id, isFavorite)
        }
    }
}