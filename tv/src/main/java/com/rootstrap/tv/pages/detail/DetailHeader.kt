package com.rootstrap.tv.pages.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text
import com.rootstrap.domain.Movie
import com.rootstrap.tv.theme.Dimens

const val DESCRIPTION_WIDTH_PROPORTION = 0.4f

@Composable
fun DetailHeader(movie: Movie) {
    Column(modifier = Modifier) {
        MovieLargeTitle(movie.name)
        Spacer(modifier = Modifier.height(Dimens.paddingNormal))
        MovieDescription(description = movie.description)
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun MovieLargeTitle(movieTitle: String) {
    Text(
        text = movieTitle,
        style = MaterialTheme.typography.displayMedium.copy(
            fontWeight = FontWeight.Bold
        ),
        maxLines = 1,
        color = MaterialTheme.colorScheme.onSurface
    )
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun MovieDescription(description: String) {
    val width = LocalConfiguration.current.screenWidthDp.dp.times(DESCRIPTION_WIDTH_PROPORTION)
    Text(
        text = description,
        style = MaterialTheme.typography.titleSmall,
        modifier = Modifier
            .width(width)
            .padding(top = Dimens.paddingHalf),
        maxLines = 3,
        color = MaterialTheme.colorScheme.onSurface
    )
}
