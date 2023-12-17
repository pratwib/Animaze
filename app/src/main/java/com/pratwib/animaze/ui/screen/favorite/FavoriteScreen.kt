package com.pratwib.animaze.ui.screen.favorite

import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.pratwib.animaze.data.local.AnimeEntity
import com.pratwib.animaze.ui.component.AvailableContent
import com.pratwib.animaze.ui.component.EmptyContent
import com.pratwib.animaze.ui.component.ErrorContent
import com.pratwib.animaze.ui.component.LoadingIndicator
import com.pratwib.animaze.util.UIState

@Composable
fun FavoriteScreen(navController: NavController, scaffoldState: ScaffoldState) {
    val favoriteViewModel = hiltViewModel<FavoriteViewModel>()

    favoriteViewModel.allFavoriteAnime.collectAsState(UIState.Loading).value.let { uiState ->
        when (uiState) {
            is UIState.Loading -> LoadingIndicator()
            is UIState.Error -> ErrorContent()
            is UIState.Success -> {
                FavoriteContent(
                    listFavoriteAnime = uiState.data,
                    navController = navController,
                    scaffoldState = scaffoldState,
                    onUpdateFavoriteAnime = favoriteViewModel::updateFavoriteAnime
                )
            }
        }
    }
}

@Composable
fun FavoriteContent(
    listFavoriteAnime: List<AnimeEntity>,
    navController: NavController,
    scaffoldState: ScaffoldState,
    onUpdateFavoriteAnime: (id: Int, isFavorite: Boolean) -> Unit
) {
    when (listFavoriteAnime.isEmpty()) {
        true -> EmptyContent()
        false -> AvailableContent(
            listFavoriteAnime,
            navController,
            scaffoldState,
            onUpdateFavoriteAnime
        )
    }
}