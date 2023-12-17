package com.pratwib.animaze.ui.component

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScaffoldState
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material.icons.rounded.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.pratwib.animaze.R
import com.pratwib.animaze.data.local.AnimeEntity
import com.pratwib.animaze.ui.navigation.Screen
import kotlinx.coroutines.launch

@Composable
fun AvailableContent(
    listAnime: List<AnimeEntity>,
    navController: NavController,
    scaffoldState: ScaffoldState,
    onUpdateFavoriteAnime: (id: Int, isFavorite: Boolean) -> Unit,
) {
    LazyColumn {
        items(listAnime, key = { it.title }) { anime ->
            AnimeItems(anime, navController, scaffoldState, onUpdateFavoriteAnime)
        }
    }
}

@Composable
fun AnimeItems(
    anime: AnimeEntity,
    navController: NavController,
    scaffoldState: ScaffoldState,
    onUpdateFavoriteAnime: (id: Int, isFavorite: Boolean) -> Unit,
) {
    val coroutineScope = rememberCoroutineScope()
    val (id, title, _, photoUrl, isFavorite) = anime

    Card(
        modifier = Modifier
            .padding(16.dp, 8.dp)
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.large)
            .border(1.dp, Color.LightGray.copy(1f), MaterialTheme.shapes.large)
            .clickable { navController.navigate(Screen.Detail.createRoute(id ?: 0)) },
    ) {
        Row {
            Box(
                modifier = Modifier
                    .clip(MaterialTheme.shapes.large)
                    .width(80.dp)
            ) {
                AsyncImage(
                    model = photoUrl,
                    contentDescription = title,
                    contentScale = ContentScale.FillWidth,
                    placeholder = painterResource(R.drawable.image_holder),
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }

            Column(modifier = Modifier.padding(16.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.h6,
                        lineHeight = 32.sp
                    )

                    Icon(
                        imageVector = if (isFavorite) Icons.Rounded.Favorite else Icons.Rounded.FavoriteBorder,
                        tint = if (isFavorite) Color.Red else MaterialTheme.colors.onSurface,
                        contentDescription = title,
                        modifier = Modifier
                            .size(32.dp)
                            .clip(RoundedCornerShape(100))
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = null
                            ) {
                                onUpdateFavoriteAnime(id ?: 0, !isFavorite)
                                coroutineScope.launch {
                                    scaffoldState.snackbarHostState.showSnackbar(
                                        message = "$title ${if (isFavorite) "removed from" else "added as"} favorite",
                                        actionLabel = "Dismiss",
                                        duration = SnackbarDuration.Short
                                    )
                                }
                            },
                    )
                }
            }
        }
    }
}