package com.rootstrap.tv.pages.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.style.TextAlign
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Text
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.rootstrap.domain.Movie
import com.rootstrap.tv.common.BorderedFocusableItem
import com.rootstrap.tv.theme.Dimens

private const val HORIZONTAL_ITEM_ASPECT_RATIO = 1.8f

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun HorizontalRowItem(
    movie: Movie,
    onItemClick: (movie: Movie?) -> Unit
) {
    BorderedFocusableItem(
        onClick = { onItemClick(movie) },
        modifier = Modifier
            .padding(horizontal = Dimens.paddingHalf)
            .aspectRatio(HORIZONTAL_ITEM_ASPECT_RATIO)
    ) {
        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(movie.posterUri)
                    .crossfade(true)
                    .build(),
                contentDescription = movie.description,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(Color.Black, Color.Transparent),
                            startY = Float.POSITIVE_INFINITY,
                            endY = 0.0f
                        )
                    )
            )
            Text(
                text = movie.name,
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(Dimens.paddingNormal)
            )
        }
    }
}
