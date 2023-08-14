package com.rootstrap.tv.pages.detail

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Glow
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text
import androidx.tv.material3.WideButton
import androidx.tv.material3.WideButtonDefaults
import com.rootstrap.domain.Movie
import com.rootstrap.tv.R
import com.rootstrap.tv.common.TvComposablePreview
import com.rootstrap.tv.common.WatchNowButton
import com.rootstrap.tv.theme.Dimens
import org.koin.androidx.compose.koinViewModel

private const val HEADER_SPACE_PROPORTION = 0.3f

@Composable
fun DetailScreen(
    movieId: String,
    viewModel: MovieDetailViewModel = koinViewModel(),
    onBackPressed: () -> Unit,
    onPlayNowClick: (Movie) -> Unit
) {
    val uiState by viewModel.uiStateFlow.collectAsState()

    BackHandler(onBack = onBackPressed)
    LaunchedEffect(key1 = true) {
        viewModel.onDetailLoad(movieId)
    }
    DetailScreen(detailUiState = uiState, onPlayNowClick = onPlayNowClick)
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun DetailScreen(
    detailUiState: DetailUiState,
    onPlayNowClick: (Movie) -> Unit
) {
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp

    detailUiState.movie?.let { movie ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            BannerImage(modifier = Modifier, movie = movie)
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = Dimens.paddingSixQuarters)
            ) {
                Spacer(modifier = Modifier.height(screenHeight.times(HEADER_SPACE_PROPORTION)))
                DetailHeader(movie = movie)
                ActionButtonsRow(movie = movie, onPlayNowClick = onPlayNowClick)
            }
        }
    }
}

@Composable
fun ActionButtonsRow(movie: Movie, onPlayNowClick: (Movie) -> Unit) {
    Row(
        verticalAlignment = Alignment.Bottom,
        modifier = Modifier
            .fillMaxWidth()
            .height(Dimens.detailButtonsRowHeight)
    ) {
        WatchNowButton(
            item = movie,
            modifier = Modifier.padding(top = Dimens.paddingNormal)
        ) {
            onPlayNowClick(movie)
        }
        WideButtonExample()
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun WideButtonExample() {
    WideButton(
        onClick = { },
        glow = WideButtonDefaults.glow(
            glow = Glow.None.copy(
                glowColor = Color.Blue,
                glowElevation = Dimens.glowElevationNormal
            ),
            focusedGlow = Glow.None.copy(
                glowColor = Color.Green,
                glowElevation = Dimens.glowElevationFocused
            ),
            pressedGlow = Glow.None.copy(
                glowColor = Color.Black,
                glowElevation = Dimens.glowElevationNormal
            )
        ),
        modifier = Modifier.padding(start = Dimens.paddingNormal)
    ) {
        Text(stringResource(R.string.add_to_favourites))
    }
}

@Preview(showBackground = true, device = Devices.TV_1080p)
@Composable
fun DetailScreenPreview() {
    TvComposablePreview {
        DetailScreen(
            detailUiState = DetailUiState(
                movie = Movie(
                    id = "1",
                    name = "Movie Name",
                    description = "Movie Description",
                    posterUri = "https://picsum.photos/200/300"
                )
            ),
            onPlayNowClick = {}
        )
    }
}
