package com.pratwib.animaze.ui.screen.home

import androidx.compose.foundation.layout.Column
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.pratwib.animaze.data.local.AnimeEntity
import com.pratwib.animaze.ui.component.AvailableContent
import com.pratwib.animaze.ui.component.EmptyContent
import com.pratwib.animaze.ui.component.ErrorContent
import com.pratwib.animaze.ui.component.LoadingIndicator
import com.pratwib.animaze.ui.component.SearchBar
import com.pratwib.animaze.util.UIState

@Composable
fun HomeScreen(navController: NavController, scaffoldState: ScaffoldState) {
    val homeViewModel = hiltViewModel<HomeViewModel>()
    val homeState by homeViewModel.homeState

    homeViewModel.allAnime.collectAsState(UIState.Loading).value.let { uiState ->
        when (uiState) {
            is UIState.Loading -> LoadingIndicator()
            is UIState.Error -> ErrorContent()
            is UIState.Success -> {
                HomeContent(
                    listAnime = uiState.data,
                    navController = navController,
                    scaffoldState = scaffoldState,
                    query = homeState.query,
                    onQueryChange = homeViewModel::onQueryChange,
                    onUpdateFavoriteAnime = homeViewModel::updateFavoriteAnime
                )
            }
        }
    }
}

@Composable
fun HomeContent(
    listAnime: List<AnimeEntity>,
    navController: NavController,
    scaffoldState: ScaffoldState,
    query: String,
    onQueryChange: (String) -> Unit,
    onUpdateFavoriteAnime: (id: Int, isFavorite: Boolean) -> Unit
) {
    Column {
        SearchBar(query = query, onQueryChange = onQueryChange)
        when (listAnime.isEmpty()) {
            true -> EmptyContent()
            false -> AvailableContent(
                listAnime,
                navController,
                scaffoldState,
                onUpdateFavoriteAnime
            )
        }
    }
}