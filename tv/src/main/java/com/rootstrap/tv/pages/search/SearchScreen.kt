package com.rootstrap.tv.pages.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text
import com.rootstrap.tv.R
import com.rootstrap.tv.common.TvComposablePreview
import com.rootstrap.tv.pages.search.keyboard.MiniKeyboard
import com.rootstrap.tv.theme.Dimens
import org.koin.androidx.compose.koinViewModel

@Composable
fun SearchScreen(viewModel: SearchViewModel = koinViewModel()) {
    val uiState by viewModel.uiStateFlow.collectAsState()

    Row(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.padding(
                horizontal = Dimens.paddingThreeQuarters,
                vertical = Dimens.paddingSixQuarters
            )
        ) {
            SearchKeyBoardHeader(uiState.query)
            MiniKeyboard(
                modifier = Modifier.width(Dimens.miniKeyboardWidth),
                isNumeric = uiState.keyBoardIsNumeric,
                onKeyPressed = { key, type -> viewModel.onKeyPressed(key, type) }
            )
        }
        SearchResultGrid()
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun SearchKeyBoardHeader(query: String) {
    Column {
        Text(
            text = query.ifEmpty { stringResource(id = R.string.search_title) },
            style = MaterialTheme.typography.titleLarge,
            color = Color.White,
            modifier = Modifier.padding(all = Dimens.paddingThreeQuarters)
        )
        Spacer(
            modifier = Modifier
                .height(1.dp)
                .background(MaterialTheme.colorScheme.onSurface)
        )
    }
}

@Preview
@Composable
fun SearchScreenPrev() {
    TvComposablePreview {
        SearchScreen()
    }
}
