package com.pratwib.animaze.ui.screen.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pratwib.animaze.data.local.AnimeData
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
class HomeViewModel @Inject constructor(private val repository: AnimeRepository) : ViewModel() {
    private val _allAnime = MutableStateFlow<UIState<List<AnimeEntity>>>(UIState.Loading)
    val allAnime = _allAnime.asStateFlow()

    private val _homeState = mutableStateOf(HomeState())
    val homeState: State<HomeState> = _homeState

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllAnime().collect { anime ->
                when (anime.isEmpty()) {
                    true -> repository.insertAllAnime(AnimeData.animeList)
                    else -> _allAnime.value = UIState.Success(anime)
                }
            }
        }
    }

    private fun searchAnime(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.searchAnime(query)
                .catch { _allAnime.value = UIState.Error(it.message.toString()) }
                .collect { _allAnime.value = UIState.Success(it) }
        }
    }

    fun updateFavoriteAnime(id: Int, isFavorite: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateFavoriteAnime(id, isFavorite)
        }
    }

    fun onQueryChange(query: String) {
        _homeState.value = _homeState.value.copy(query = query)
        searchAnime(query)
    }
}