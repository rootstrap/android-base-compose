package com.rootstrap.tv.pages.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.MaterialTheme
import coil.compose.AsyncImage
import com.rootstrap.domain.Movie

private const val GRADIENT_VERTICAL_START = 0.5f
private const val BANNER_IMAGE_HEIGHT_PROPORTION = 0.7f

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun BannerImage(modifier: Modifier, movie: Movie) {
    val height = LocalConfiguration.current.screenHeightDp.dp.times(BANNER_IMAGE_HEIGHT_PROPORTION)
    Box(modifier = modifier.height(height)) {
        AsyncImage(
            model = movie.posterUri,
            contentDescription = movie.description,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            MaterialTheme.colorScheme.background
                        ),
                        startY = GRADIENT_VERTICAL_START
                    )
                )
        )
    }
}
