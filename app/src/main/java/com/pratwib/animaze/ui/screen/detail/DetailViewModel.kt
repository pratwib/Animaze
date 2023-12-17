package com.pratwib.animaze.ui.screen.detail

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
class DetailViewModel @Inject constructor(private val repository: AnimeRepository) : ViewModel() {
    private val _anime = MutableStateFlow<UIState<AnimeEntity>>(UIState.Loading)
    val anime = _anime.asStateFlow()

    fun getAnime(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAnime(id)
                .catch { _anime.value = UIState.Error(it.message.toString()) }
                .collect { _anime.value = UIState.Success(it) }
        }
    }

    fun updateFavoriteAnime(id: Int, isFavorite: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateFavoriteAnime(id, isFavorite)
        }
    }
}