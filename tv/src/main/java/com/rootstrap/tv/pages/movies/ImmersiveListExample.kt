@file:OptIn(ExperimentalTvMaterial3Api::class)

package com.rootstrap.tv.pages.movies

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.focusGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.tv.foundation.lazy.list.TvLazyRow
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.ImmersiveList
import androidx.tv.material3.ImmersiveListScope
import com.rootstrap.domain.Movie
import com.rootstrap.tv.theme.ComposeTVTheme
import com.rootstrap.tv.theme.Dimens
import com.rootstrap.tv.theme.Dimens.immersiveListHeight

private const val IMMERSIVE_LIST_PROPORTION = 0.7f
private const val ANIMATION_DURATION = 1000

/**
 * Shows a background image behind the list
 * */
@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun ImmersiveListExample(
    modifier: Modifier,
    featuredMovies: List<Movie>,
    onItemClick: (Movie) -> Unit
) {
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
        }
    ) {
        Column {
            if (isListFocused) {
                val movie = featuredMovies[currentItemIndex]
                ImmersiveListPosterDescription(movie = movie)
            }
            ImmersiveListRow(
                modifier = Modifier,
                featuredMovies = featuredMovies,
                onItemClick = onItemClick
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
    onItemClick: (Movie) -> Unit,
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
                    onMovieClick = onItemClick,
                    focusedItemIndex = currentItemIndex
                )
            }
        }
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
            modifier = Modifier,
            onItemClick = {}
        )
    }
}
