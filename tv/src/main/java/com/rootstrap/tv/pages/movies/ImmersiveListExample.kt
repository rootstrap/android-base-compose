@file:OptIn(ExperimentalTvMaterial3Api::class)

package com.rootstrap.tv.pages.movies

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.focusGroup
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.tv.foundation.lazy.list.TvLazyRow
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.ImmersiveList
import androidx.tv.material3.ImmersiveListScope
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.rootstrap.domain.Movie
import com.rootstrap.tv.theme.ComposeTVTheme
import com.rootstrap.tv.theme.Dimens
import com.rootstrap.tv.theme.Dimens.immersiveListHeight

private const val IMMERSIVE_LIST_PROPORTION = 0.6f
private const val POSTER_DESCRIPTION_PROPORTION = 0.5f
private const val ANIMATION_DURATION = 1000

/**
 * Shows a background image behind the list
 * */
@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun ImmersiveListExample(modifier: Modifier, featuredMovies: List<Movie>) {
    var currentItemIndex by remember { mutableIntStateOf(0) }
    var isListFocused by remember { mutableStateOf(false) }
    val immersiveListHeight =
        LocalConfiguration.current.screenHeightDp.times(IMMERSIVE_LIST_PROPORTION).dp

    ImmersiveList(
        modifier = modifier,
        listAlignment = Alignment.BottomStart,
        background = { index, hasFocus ->
            isListFocused = hasFocus
            AnimatedContent(targetState = index) {
                AnimatedVisibility(
                    visible = hasFocus,
                    enter = fadeIn(animationSpec = tween(ANIMATION_DURATION)),
                    exit = fadeOut(animationSpec = tween(ANIMATION_DURATION)),
                    modifier = Modifier.height(immersiveListHeight)
                ) {
                    ImmersiveListPoster(featuredMovies, index, immersiveListHeight)
                }
            }
        },
    ) {
        Column {
            if (isListFocused) {
                val movie = featuredMovies[currentItemIndex]
                ImmersiveListPosterDescription(movie = movie)
            }
            ImmersiveListRow(
                modifier = Modifier,
                featuredMovies = featuredMovies
            ) { index ->
                currentItemIndex = index
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalTvMaterial3Api::class)
@Composable
fun ImmersiveListScope.ImmersiveListRow(
    modifier: Modifier = Modifier,
    featuredMovies: List<Movie>,
    currentItemIndex: (index: Int) -> Unit
) {
    Column(modifier = modifier.focusGroup()) {
        TvLazyRow(
            modifier = Modifier.height(immersiveListHeight),
            contentPadding = PaddingValues(
                start = Dimens.paddingDouble,
                top = Dimens.paddingHalf,
                bottom = Dimens.paddingNormal,
                end = Dimens.paddingDouble
            )
        ) {
            items(featuredMovies.size) { index ->
                ImmersiveListItem(
                    modifier = Modifier.immersiveListItem(index),
                    index = index,
                    movie = featuredMovies[index],
                    onMovieClick = { },
                    focusedItemIndex = currentItemIndex
                )
            }
        }
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun ImmersiveListPosterDescription(movie: Movie) {
    Column(
        modifier = Modifier.padding(
            start = Dimens.paddingNormal,
            bottom = Dimens.paddingDouble
        )
    ) {
        Text(
            text = movie.name,
            style = MaterialTheme.typography.displaySmall,
            color = MaterialTheme.colorScheme.onSurface
        )
        Spacer(modifier = Modifier.padding(top = Dimens.paddingHalf))
        Text(
            modifier = Modifier.fillMaxWidth(POSTER_DESCRIPTION_PROPORTION),
            text = movie.description,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.75f),
            fontWeight = FontWeight.Light
        )
    }
}

@Composable
fun ImmersiveListPoster(featuredMovies: List<Movie>, index: Int, height: Dp) {
    val gradient = Brush.verticalGradient(
        colors = listOf(Color.Transparent, Color.Black),
        startY = 0f,
        endY = LocalConfiguration.current.screenWidthDp.toFloat()
    )
    Box {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .height(height),
            model = if (featuredMovies.size > index) {
                ImageRequest.Builder(LocalContext.current)
                    .crossfade(true)
                    .data(featuredMovies[index].posterUri)
                    .build()
            } else null,
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Box(
            modifier = Modifier
                .matchParentSize()
                .background(gradient)
        )
    }
}

@Composable
@Preview(showBackground = true, device = Devices.TV_1080p)
fun ImmersiveListExamplePreview() {
    val list: MutableList<Movie> = mutableListOf()
    for (i in 0..10) {
        list.add(
            Movie(
                id = i.toString(),
                posterUri = "https://storage.googleapis.com/androiddevelopers/samples/media/posters/2_3-300/the-good-laywer.jpg",
                name = "Teminator II",
                description = "Terminator is an American media franchise created by James Cameron and Gale Anne Hurd. The franchise encompasses a series of science fiction action films"
            )
        )
    }
    ComposeTVTheme {
        ImmersiveListExample(
            featuredMovies = list,
            modifier = Modifier
        )
    }
}
