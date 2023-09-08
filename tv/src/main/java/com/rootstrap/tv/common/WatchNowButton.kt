package com.rootstrap.tv.common

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.tv.material3.Button
import androidx.tv.material3.ButtonDefaults
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Icon
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.ShapeDefaults
import androidx.tv.material3.Text
import com.rootstrap.tv.R
import com.rootstrap.tv.theme.Dimens

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun <T> WatchNowButton(modifier: Modifier = Modifier, item: T, onClick: (T) -> Unit) {
    Button(
        onClick = { onClick(item) },
        modifier = modifier.padding(top = Dimens.paddingHalf),
        contentPadding = ButtonDefaults.ButtonWithIconContentPadding,
        shape = ButtonDefaults.shape(shape = ShapeDefaults.ExtraSmall),
        colors = ButtonDefaults.colors(
            containerColor = MaterialTheme.colorScheme.onSurface,
            contentColor = MaterialTheme.colorScheme.surface,
            focusedContentColor = MaterialTheme.colorScheme.surface
        ),
        scale = ButtonDefaults.scale(scale = 1f)
    ) {
        Icon(
            imageVector = Icons.Outlined.PlayArrow,
            contentDescription = null,
            modifier = Modifier.padding(end = Dimens.paddingHalf)
        )

        Text(
            text = stringResource(R.string.watch_now),
            style = MaterialTheme.typography.titleSmall
        )
    }
}
