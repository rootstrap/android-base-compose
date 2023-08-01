package com.rootstrap.tv.pages.search

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.tv.foundation.lazy.grid.TvGridCells
import androidx.tv.foundation.lazy.grid.TvGridItemSpan
import androidx.tv.foundation.lazy.grid.TvLazyVerticalGrid
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text
import com.rootstrap.tv.R
import com.rootstrap.tv.pages.components.VerticalListItem
import com.rootstrap.tv.theme.Dimens

@Composable
fun SearchResultGrid(modifier: Modifier = Modifier) {
    TvLazyVerticalGrid(
        modifier = modifier,
        columns = TvGridCells.Fixed(3),
        contentPadding = PaddingValues(
            start = Dimens.paddingThreeQuarters,
            top = Dimens.paddingSixQuarters,
            end = Dimens.paddingThreeQuarters,
            bottom = Dimens.paddingTenQuarters
        )
    ) {
        item(span = {
            TvGridItemSpan(3)
        }) {
            SearchResultHeader()
        }
        items(30) {
            VerticalListItem(parent = 0, child = 0) { a, b -> }
        }
    }
}

@Composable
fun SearchResultHeader() {
    Text(
        text = stringResource(id = R.string.search_results),
        color = Color.White,
        style = MaterialTheme.typography.titleLarge,
        modifier = Modifier.padding(bottom = Dimens.paddingSixQuarters, start = Dimens.paddingHalf)
    )
}