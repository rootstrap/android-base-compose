package com.rootstrap.tv.pages.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.tv.foundation.lazy.list.TvLazyRow
import androidx.tv.foundation.lazy.list.itemsIndexed
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text
import com.rootstrap.domain.Movie
import com.rootstrap.tv.pages.components.HorizontalRowItem
import com.rootstrap.tv.theme.Dimens

@Composable
fun HorizontalRowWithTitle(
    rowName: String,
    rowItems: SnapshotStateList<Movie>,
    onItemClick: (Movie?) -> Unit
) {
    Column(Modifier.height(Dimens.homeRowHeight)) {
        RowTitle(title = rowName)
        TvLazyRow(
            contentPadding = PaddingValues(
                start = Dimens.paddingNormal,
                top = Dimens.paddingHalf,
                bottom = Dimens.paddingHalf,
                end = Dimens.paddingThirtyQuarters
            )
        ) {
            itemsIndexed(rowItems) { _, item ->
                HorizontalRowItem(
                    movie = item,
                    onItemClick = onItemClick
                )
            }
        }
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun RowTitle(title: String) {
    Text(
        text = title,
        modifier = Modifier
            .padding(horizontal = Dimens.paddingSixQuarters)
            .padding(vertical = Dimens.paddingNormal),
        color = Color.White,
        style = MaterialTheme.typography.titleMedium
    )
}
