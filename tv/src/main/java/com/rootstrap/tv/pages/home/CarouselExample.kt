package com.rootstrap.tv.pages.home

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.tv.material3.Carousel
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.ShapeDefaults
import androidx.tv.material3.Text
import coil.compose.AsyncImage
import com.rootstrap.domain.Movie
import com.rootstrap.tv.common.TvComposablePreview
import com.rootstrap.tv.common.WatchNowButton
import com.rootstrap.tv.theme.Dimens
import com.rootstrap.tv.utils.Constants.CAROUSEL_ANIM_DURATION

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun CarouselExample(featuredContentList: List<Movie>, onPlayNowClicked: (Movie) -> Unit) {
    val carouselHeight = LocalConfiguration.current.screenHeightDp.dp.times(0.60f)
    var isCarouselFocused by remember { mutableStateOf(false) }
    val carouselBorderAlpha by animateFloatAsState(
        targetValue = if (isCarouselFocused) 1f else 0f,
        label = ""
    )

    AnimatedContent(
        targetState = featuredContentList,
        label = "Carousel animation",
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = Dimens.paddingQuadruple,
                end = Dimens.paddingQuadruple,
                top = Dimens.paddingNormal
            )
            .height(carouselHeight)
            .border(
                width = Dimens.carouselBorderWidth,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = carouselBorderAlpha),
                shape = ShapeDefaults.Medium
            )
            .clip(ShapeDefaults.Medium)
            .onFocusChanged {
                isCarouselFocused = it.hasFocus
            }
    ) { movies ->
        Carousel(
            itemCount = movies.size,
            modifier = Modifier.fillMaxSize(),
            contentTransformEndToStart = fadeIn(tween(CAROUSEL_ANIM_DURATION)).togetherWith(
                fadeOut(
                    tween(CAROUSEL_ANIM_DURATION)
                )
            ),
            contentTransformStartToEnd = fadeIn(tween(CAROUSEL_ANIM_DURATION)).togetherWith(
                fadeOut(
                    tween(CAROUSEL_ANIM_DURATION)
                )
            )
        ) { index ->
            val content = movies[index]
            CarouselItem(
                modifier = Modifier.fillMaxSize(),
                movie = content,
                isCarouselFocused = isCarouselFocused,
                onPlayNowClicked = onPlayNowClicked
            )
        }
    }
}

@Composable
fun CarouselItem(
    modifier: Modifier,
    movie: Movie,
    isCarouselFocused: Boolean,
    onPlayNowClicked: (Movie) -> Unit
) {
    Box(modifier = modifier) {
        AsyncImage(
            model = movie.posterUri,
            contentDescription = movie.description,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        CarouselItemText(
            movie = movie,
            isCarouselFocused = isCarouselFocused,
            onPlayNowClicked = onPlayNowClicked
        )
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun CarouselItemText(
    movie: Movie,
    isCarouselFocused: Boolean,
    onPlayNowClicked: (Movie) -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomStart
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(Dimens.paddingDouble),
            verticalArrangement = Arrangement.Bottom
        ) {
            Text(
                text = movie.name,
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.displayMedium.copy(
                    shadow = Shadow(
                        color = Color.Black.copy(alpha = 0.5f),
                        blurRadius = 2f
                    )
                ),
                maxLines = 1
            )
            Text(
                text = movie.description,
                style = MaterialTheme.typography.titleMedium.copy(
                    color = MaterialTheme.colorScheme.onSurface.copy(
                        alpha = 0.65f
                    ),
                    shadow = Shadow(
                        color = Color.Black.copy(alpha = 0.5f),
                        blurRadius = 2f
                    )
                ),
                maxLines = 1,
                modifier = Modifier.padding(top = Dimens.paddingHalf)
            )
            AnimatedVisibility(visible = isCarouselFocused, content = {
                WatchNowButton(item = movie) {
                    onPlayNowClicked(movie)
                }
            })
        }
    }
}

@Composable
@Preview
fun CarouselPreview() {
    TvComposablePreview {
        CarouselExample(
            featuredContentList = listOf(
                getMoviePreview()
            ),
            onPlayNowClicked = { }
        )
    }
}

fun getMoviePreview(): Movie {
    return Movie(
        id = "4",
        posterUri = "https://storage.googleapis.com/androiddevelopers/samples/media/posters/2_3-300/the-good-laywer.jpg",
        name = "Teminator II",
        description = "Terminator is an American media franchise created by James Cameron and Gale Anne Hurd. The franchise encompasses a series of science fiction action films"
    )
}
