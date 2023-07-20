package com.rootstrap.tv.pages.home.navigationdrawer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.NavigationDrawer
import androidx.tv.material3.Text
import com.rootstrap.tv.common.TvComposablePreview
import com.rootstrap.tv.pages.home.navigationdrawer.navigation.NestedScreens
import com.rootstrap.tv.theme.Dimens


@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun HomeNavigationDrawer(
    content: @Composable () -> Unit,
    onMenuSelected: ((menuItem: MenuItem) -> Unit)?
) {
    NavigationDrawer(
        drawerContent = { drawer ->
            Column(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.surface)
                    .fillMaxHeight()
                    .padding(Dimens.paddingThreeQuarters),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(Dimens.paddingNormal))
                NestedScreens.values().forEach { menuItem ->
                    HomeDrawerRow(drawer, menuItem, Modifier) {
                        onMenuSelected?.invoke(menuItem)
                    }
                }
            }
        },
        content = content
    )
}


@Preview
@Composable
fun HomeDrawerPrev() {
    TvComposablePreview {
        HomeNavigationDrawer(content = {
            Text(text = "Home Drawer")
        }, onMenuSelected = null)
    }
}
