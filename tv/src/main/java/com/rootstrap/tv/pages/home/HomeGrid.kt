package com.rootstrap.tv.pages.home

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.tv.foundation.lazy.list.TvLazyColumn
import com.rootstrap.domain.Movie
import com.rootstrap.tv.theme.Dimens

@Composable
fun HomeGrid(
    uiState: MovieUiState,
    modifier: Modifier,
    onItemClick: (Movie?) -> Unit
) {
    TvLazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(bottom = Dimens.paddingThirtyQuarters)
    ) {
        item {
            CarouselExample(
                featuredContentList = uiState.featuredMovies,
                onPlayNowClicked = onItemClick,
            )
        }
    }
}

@Composable
@Preview(showBackground = true, device = Devices.TV_1080p)
fun HomeGridPreview() {
    val uiState = MovieUiState(
        featuredMovies = getMoviesPreview()
    )
    HomeGrid(uiState = uiState, modifier = Modifier, onItemClick = {})
}

fun getMoviesPreview(): SnapshotStateList<Movie> {
    val list: SnapshotStateList<Movie> = SnapshotStateList()
    for (i in 0..10) {
        list.add(
            getMoviePreview()
        )
    }
    return list
}
