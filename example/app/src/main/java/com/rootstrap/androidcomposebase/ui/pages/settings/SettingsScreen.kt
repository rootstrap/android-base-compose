package com.rootstrap.androidcomposebase.ui.pages.settings

import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Nightlight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.rootstrap.androidcomposebase.ui.theme.AppTheme
import com.rootstrap.androidcomposebase.ui.theme.compactDimensions
import com.rootstrap.androidcomposebase.ui.theme.defaultDimensions
import com.rootstrap.androidcomposebase.ui.theme.expandedDimensions
import com.rootstrap.example.app.R

private const val ANIMATION_TIME_IN_MILLIS = 300

@Composable
fun SettingsScreen(email: String, isOSDarkTheme: Boolean, onThemeUpdated: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
    ) {
        CenterAlignedAppBar()
        Text(stringResource(R.string.email, email), Modifier.padding(16.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(defaultDimensions.big)
        ) {
            Text(
                text = stringResource(id = R.string.settings_dark_light_theme),
                modifier = Modifier.weight(1f)
            )
            ThemeSwitcher(
                isOSDarkTheme = isOSDarkTheme,
                size = expandedDimensions.paddingSixQuarters,
                padding = compactDimensions.paddingFiveQuarters,
                onClick = onThemeUpdated
            )
        }
    }
}

// Opt-in to use experimental Material 3 API
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CenterAlignedAppBar() {
    // TopAppBar is a composable that represents a top app bar.
    TopAppBar(
        // The title of the top app bar is set to "My App".
        title = {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.CenterStart
            ) {
                Text(stringResource(id = R.string.settings_title))
            }
        },

        // The navigation icon is set to a menu icon.
        navigationIcon = {
            IconButton(onClick = { /* Handle navigation icon click */ }) {
                Icon(
                    Icons.Filled.Menu,
                    contentDescription = stringResource(id = R.string.content_description_navigation_icon)
                )
            }
        }
    )
}

@Composable
fun ThemeSwitcher(
    isOSDarkTheme: Boolean = false,
    size: Dp = expandedDimensions.paddingTwentyQuarters,
    iconSize: Dp = size / 3,
    padding: Dp = defaultDimensions.paddingEightQuarters,
    borderWidth: Dp = defaultDimensions.small,
    parentShape: Shape = CircleShape,
    toggleShape: Shape = CircleShape,
    animationSpec: AnimationSpec<Dp> = tween(durationMillis = ANIMATION_TIME_IN_MILLIS),
    onClick: () -> Unit
) {
    val offset by animateDpAsState(
        targetValue = if (isOSDarkTheme) 0.dp else size,
        animationSpec = animationSpec,
        label = ""
    )
    Box(
        modifier = Modifier
            .width(size * 2)
            .height(size)
            .clip(shape = parentShape)
            .clickable { onClick() }
            .background(MaterialTheme.colorScheme.secondaryContainer)
    ) {
        Box(
            modifier = Modifier
                .size(size)
                .offset(x = offset)
                .padding(all = padding)
                .clip(shape = toggleShape)
                .background(MaterialTheme.colorScheme.primary)
        ) {}
        Row(
            modifier = Modifier
                .border(
                    border = BorderStroke(
                        width = borderWidth,
                        color = MaterialTheme.colorScheme.primary
                    ),
                    shape = parentShape
                )
        ) {
            Box(
                modifier = Modifier.size(size),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    modifier = Modifier.size(iconSize),
                    imageVector = Icons.Default.Nightlight,
                    contentDescription = stringResource(id = R.string.content_description_theme_icon),
                    tint = MaterialTheme.colorScheme.inverseSurface
                )
            }
            Box(
                modifier = Modifier.size(size),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    modifier = Modifier.size(iconSize),
                    imageVector = Icons.Default.LightMode,
                    contentDescription = stringResource(id = R.string.content_description_theme_icon),
                    tint = MaterialTheme.colorScheme.inversePrimary
                )
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun SettingsScreenPreview() {
    val boolean = isSystemInDarkTheme()
    var darkTheme by remember { mutableStateOf(boolean) }
    AppTheme {
        SettingsScreen(
            email = "",
            isOSDarkTheme = darkTheme,
            onThemeUpdated = { darkTheme = !darkTheme }
        )
    }
}
