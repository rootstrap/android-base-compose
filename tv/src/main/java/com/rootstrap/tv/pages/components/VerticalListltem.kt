package com.rootstrap.tv.pages.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Text
import com.rootstrap.tv.common.BorderedFocusableItem
import com.rootstrap.tv.theme.Dimens

private const val VERTICAL_ITEM_ASPECT_RATIO = 0.6f

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun VerticalListItem(itemText: String, onItemFocus: () -> Unit) {
    BorderedFocusableItem(
        onClick = {
            onItemFocus()
        },
        modifier = Modifier
            .padding(Dimens.paddingHalf)
            .aspectRatio(VERTICAL_ITEM_ASPECT_RATIO)
    ) {
        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
            Text(text = itemText, textAlign = TextAlign.Center)
        }
    }
}
