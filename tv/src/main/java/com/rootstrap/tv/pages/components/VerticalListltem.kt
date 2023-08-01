package com.rootstrap.tv.pages.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.tv.material3.Text
import com.rootstrap.tv.common.BorderedFocusableItem
import com.rootstrap.tv.theme.Dimens

@Composable
fun VerticalListItem(parent: Int, child: Int, onItemFocus: (parent: Int, child: Int) -> Unit) {
    BorderedFocusableItem(
        onClick = {
            onItemFocus(parent, child)
        },
        modifier = Modifier
            .padding(Dimens.paddingHalf)
            .aspectRatio(0.6f)
    ) {
        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
            Text(text = "Item $parent x $child", textAlign = TextAlign.Center)
        }
    }
}