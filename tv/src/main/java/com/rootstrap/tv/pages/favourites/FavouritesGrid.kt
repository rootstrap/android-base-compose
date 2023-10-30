package com.rootstrap.tv.pages.favourites

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.tv.foundation.lazy.grid.TvGridCells
import androidx.tv.foundation.lazy.grid.TvLazyVerticalGrid
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text
import com.rootstrap.domain.Movie
import com.rootstrap.tv.R
import com.rootstrap.tv.pages.components.HorizontalRowItem
import com.rootstrap.tv.theme.ComposeTVTheme
import com.rootstrap.tv.theme.Dimens

private const val GRID_SIZE = 5

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun FavouritesGrid(
    modifier: Modifier,
    favouriteRows: List<Movie>,
    onItemClick: (movie: Movie?) -> Unit
) {
    Column(modifier = modifier) {
        Text(
            text = stringResource(R.string.my_favourites),
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(
                    start = Dimens.paddingTenQuarters,
                    top = Dimens.paddingTenQuarters
                ),
            color = MaterialTheme.colorScheme.onSurface
        )

        TvLazyVerticalGrid(
            contentPadding = PaddingValues(
                start = Dimens.paddingNormal,
                top = Dimens.paddingHalf,
                bottom = Dimens.paddingHalf,
                end = Dimens.paddingNormal
            ),
            modifier = Modifier
                .padding(top = Dimens.paddingNormal),
            columns = TvGridCells.Fixed(GRID_SIZE)
        ) {
            items(favouriteRows.size) {
                Column {
                    val movie = favouriteRows[it]
                    HorizontalRowItem(movie = movie, onItemClick = onItemClick)
                    Spacer(modifier = Modifier.height(Dimens.paddingNormal))
                }
            }
        }
    }
}

@Composable
@Preview
fun MyFavouritesPreview() {
    val list: MutableList<Movie> = mutableListOf()
    for (i in 0..20) {
        list.add(
            Movie(
                id = i.toString(),
                posterUri = "",
                name = "Teminator",
                description = "Terminator is an American "
            )
        )
    }
    ComposeTVTheme {
        FavouritesGrid(
            Modifier.fillMaxSize(),
            list,
            {}
        )
    }
}
