package com.rootstrap.tv.pages.home.navigationdrawer

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.tooling.preview.Preview
import androidx.tv.material3.DrawerValue
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.NavigationDrawer
import androidx.tv.material3.Text
import androidx.tv.material3.rememberDrawerState
import com.rootstrap.tv.common.TvComposablePreview
import com.rootstrap.tv.pages.home.navigationdrawer.navigation.NestedScreens
import com.rootstrap.tv.theme.Dimens

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun HomeNavigationDrawer(
    content: @Composable () -> Unit,
    onMenuSelected: ((menuItem: MenuItem) -> Unit)?,
    onBackPressed: () -> Unit
) {
    var drawerHasFocus by remember { mutableStateOf(false) }
    val focusRequesterIndex = remember { mutableStateMapOf<Int, FocusRequester>() }
    var currentFocusPosition by remember {
        mutableIntStateOf(0)
    }
    val focusRequester = remember { FocusRequester() }
    BackHandler {
        if (drawerHasFocus) {
            onBackPressed()
        } else {
            focusRequesterIndex[currentFocusPosition]?.requestFocus()
        }
    }
    LaunchedEffect(key1 = true) {
        focusRequester.requestFocus()
    }
    NavigationDrawer(
        drawerState = if (drawerHasFocus) {
            rememberDrawerState(DrawerValue.Open)
        } else {
            rememberDrawerState(DrawerValue.Closed)
        },
        drawerContent = { drawer ->
            Column(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.surface)
                    .fillMaxHeight()
                    .onFocusChanged { drawerHasFocus = it.hasFocus }
                    .padding(Dimens.paddingThreeQuarters)
                    .focusRequester(focusRequester),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(Dimens.paddingNormal))
                NestedScreens.values().forEachIndexed { index, menuItem ->
                    val rowFocusRequester = FocusRequester()
                    focusRequesterIndex[index] = rowFocusRequester
                    HomeDrawerRow(
                        drawerValue = drawer,
                        menu = menuItem,
                        modifier = Modifier.focusRequester(rowFocusRequester),
                        position = index,
                        onFocusChanged = { currentFocusPosition = it }
                    ) {
                        onMenuSelected?.invoke(menuItem)
                    }
                }
            }
        },
        content = content
    )
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Preview
@Composable
fun HomeDrawerPrev() {
    TvComposablePreview {
        HomeNavigationDrawer(
            content = {
                Text(text = "Home Drawer")
            },
            onMenuSelected = null,
            onBackPressed = {}
        )
    }
}
