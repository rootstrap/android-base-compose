package com.rootstrap.tv.pages.movies

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.tv.material3.Border
import androidx.tv.material3.CardDefaults
import androidx.tv.material3.CardLayoutDefaults
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.StandardCardLayout
import androidx.tv.material3.Text
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.rootstrap.domain.Movie
import com.rootstrap.tv.theme.ComposeTVTheme
import com.rootstrap.tv.theme.Dimens
import com.rootstrap.tv.theme.Dimens.immersiveListItemWidth
import com.rootstrap.tv.theme.Shapes
import com.rootstrap.tv.utils.Constants.ASPECT_RATIO_16_9

const val IMMERSIVE_FOCUSABLE_SCALE = 1f

@Composable
@OptIn(ExperimentalTvMaterial3Api::class)
fun ImmersiveListItem(
    modifier: Modifier = Modifier,
    index: Int,
    onMovieClick: (movie: Movie) -> Unit,
    focusedItemIndex: (index: Int) -> Unit,
    movie: Movie
) {
    var isItemFocused by remember { mutableStateOf(false) }

    StandardCardLayout(
        modifier = modifier
            .width(immersiveListItemWidth)
            .onFocusChanged {
                isItemFocused = it.isFocused
                focusedItemIndex(index)
            },
        title = {
            Text(text = movie.name, color = Color.White)
        },
        imageCard = { interactionSource ->
            ImmersiveItemCardImage(
                interactionSource = interactionSource,
                onMovieClick = onMovieClick,
                movie = movie
            )
        }
    )
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun ImmersiveItemCardImage(
    interactionSource: MutableInteractionSource,
    onMovieClick: (movie: Movie) -> Unit,
    movie: Movie
) {
    CardLayoutDefaults.ImageCard(
        onClick = { onMovieClick(movie) },
        shape = CardDefaults.shape(Shapes.SmallCardShape),
        border = CardDefaults.border(
            focusedBorder = Border(
                border = BorderStroke(
                    width = Dimens.cardBorder,
                    color = MaterialTheme.colorScheme.onSurface
                ),
                shape = Shapes.SmallCardShape
            )
        ),
        scale = CardDefaults.scale(focusedScale = IMMERSIVE_FOCUSABLE_SCALE),
        interactionSource = interactionSource
    ) {
        ImmersiveItemImage(
            modifier = Modifier.aspectRatio(ASPECT_RATIO_16_9),
            movie = movie
        )
    }
}

@Composable
private fun ImmersiveItemImage(
    modifier: Modifier = Modifier,
    movie: Movie
) {
    Box(contentAlignment = Alignment.CenterStart) {
        AsyncImage(
            modifier = modifier
                .fillMaxWidth()
                .drawWithCache {
                    onDrawWithContent {
                        drawContent()
                    }
                },
            model = ImageRequest.Builder(LocalContext.current)
                .crossfade(true)
                .data(movie.posterUri)
                .build(),
            contentDescription = "movie poster of ${movie.name}",
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
@Preview(showBackground = true)
fun ImmersiveListItemPreview() {
    ComposeTVTheme {
        ImmersiveListItem(
            index = 0,
            onMovieClick = {},
            focusedItemIndex = {},
            movie = Movie(
                id = "1",
                posterUri = "https://storage.googleapis.com/androiddevelopers/samples/media/posters/2_3-300/the-good-laywer.jpg",
                name = "Teminator II",
                description = "Terminator is an American media franchise created by James Cameron and Gale Anne Hurd. The franchise encompasses a series of science fiction action films"
            )
        )
    }
}
