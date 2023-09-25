@file:OptIn(ExperimentalTvMaterial3Api::class)

package com.rootstrap.tv.pages.search.keyboard

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.tv.foundation.lazy.grid.TvGridCells
import androidx.tv.foundation.lazy.grid.TvGridItemSpan
import androidx.tv.foundation.lazy.grid.TvLazyVerticalGrid
import androidx.tv.foundation.lazy.grid.items
import androidx.tv.foundation.lazy.list.TvLazyColumn
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Icon
import androidx.tv.material3.Text
import com.rootstrap.tv.R
import com.rootstrap.tv.common.TvComposablePreview
import com.rootstrap.tv.pages.search.KeyboardLetterType
import com.rootstrap.tv.theme.Dimens
import com.rootstrap.tv.utils.KeysGenerator

private const val KEYS_IN_ROW = 7
private const val EXTRA_KEY_ASPECT_RATIO = 2f
private const val EXTRA_KEY_WIDTH = 1.5f
private const val EXTRA_KEY_SPAN = 2

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun MiniKeyboard(
    modifier: Modifier,
    isNumeric: Boolean,
    onKeyPressed: (String, KeyboardLetterType) -> Unit
) {
    var sizeInDp by remember { mutableStateOf(DpSize.Zero) }
    val density = LocalDensity.current

    val extraKeyHeight by remember {
        derivedStateOf {
            sizeInDp.width / KEYS_IN_ROW
        }
    }

    Row(modifier = Modifier.padding(Dimens.paddingHalf)) {
        TvLazyVerticalGrid(
            modifier = modifier
                .onSizeChanged {
                    sizeInDp = density.run {
                        DpSize(
                            it.width.toDp(),
                            it.height.toDp()
                        )
                    }
                },
            columns = TvGridCells.Fixed(KEYS_IN_ROW)
        ) {
            val keys: List<Any> = if (isNumeric) {
                KeysGenerator.numbers
            } else {
                KeysGenerator.alphabet + KeysGenerator.specialCharV1
            }

            items(keys) {
                KeyboardItem(
                    key = it,
                    onKeyPressed = onKeyPressed,
                    keyboardLetterType = KeyboardLetterType.AlphaNumeric
                )
            }

            item(span = { TvGridItemSpan(EXTRA_KEY_SPAN) }) {
                KeyboardItem(
                    modifier = Modifier.aspectRatio(EXTRA_KEY_ASPECT_RATIO),
                    onKeyPressed = onKeyPressed,
                    keyboardLetterType = KeyboardLetterType.AlphaNumeric,
                    key = " "
                ) {
                    Icon(
                        painterResource(id = R.drawable.ic_space_bar),
                        contentDescription = stringResource(R.string.space)
                    )
                }
            }

            item(span = { TvGridItemSpan(EXTRA_KEY_SPAN) }) {
                KeyboardItem(
                    modifier = Modifier.aspectRatio(EXTRA_KEY_ASPECT_RATIO),
                    onKeyPressed = onKeyPressed,
                    keyboardLetterType = KeyboardLetterType.Clear,
                    key = stringResource(R.string.clear)
                ) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowLeft,
                        contentDescription = stringResource(R.string.backspace)
                    )
                }
            }
        }
        TvLazyColumn {
            item {
                KeyboardItem(
                    modifier = Modifier
                        .width(extraKeyHeight * EXTRA_KEY_WIDTH)
                        .height(extraKeyHeight),
                    onKeyPressed = onKeyPressed,
                    keyboardLetterType = KeyboardLetterType.ChangeInputType,
                    key = "inputType"
                ) {
                    Text(text = stringResource(R.string.keyboard_number))
                }
            }
        }
    }
}

@Preview(showBackground = true, device = Devices.TV_1080p)
@Composable
fun MiniKeyboardPrev() {
    TvComposablePreview {
        MiniKeyboard(
            modifier = Modifier.size(400.dp),
            isNumeric = false,
            onKeyPressed = { _, _ -> }
        )
    }
}
