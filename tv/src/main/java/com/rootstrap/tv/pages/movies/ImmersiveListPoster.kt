package com.rootstrap.tv.pages.movies

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.rootstrap.domain.Movie
import com.rootstrap.tv.theme.Dimens

private const val POSTER_DESCRIPTION_PROPORTION = 0.5f

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
            } else {
                null
            },
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
            fontWeight = FontWeight.Light,
            maxLines = 4,
            overflow = TextOverflow.Ellipsis
        )
    }
}
