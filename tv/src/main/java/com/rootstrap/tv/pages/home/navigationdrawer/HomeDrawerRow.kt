package com.rootstrap.tv.pages.home.navigationdrawer

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.keyframes
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.unit.dp
import androidx.tv.material3.DrawerValue
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Icon
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Surface
import androidx.tv.material3.Text
import com.rootstrap.tv.theme.Dimens
import com.rootstrap.tv.utils.Constants.NAV_DRAWER_ANIM_MILLIS

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun HomeDrawerRow(
    drawerValue: DrawerValue,
    menu: MenuItem,
    modifier: Modifier,
    position: Int,
    onFocusChanged: (Int) -> Unit,
    onMenuSelected: ((menuItem: MenuItem) -> Unit)?
) {
    val padding = animateDpAsState(
        animationSpec = keyframes {
            this.delayMillis = NAV_DRAWER_ANIM_MILLIS
        },
        targetValue = if (drawerValue == DrawerValue.Open) Dimens.paddingQuarter else 0.dp
    )

    Surface(
        onClick = { onMenuSelected?.invoke(menu) },
        modifier = modifier
            .onFocusChanged { onFocusChanged(position) }
            .padding(vertical = Dimens.paddingQuarter)
            .then(if (drawerValue == DrawerValue.Open) modifier.width(Dimens.navDrawerItemWidth) else modifier)
    ) {
        Row(
            modifier = Modifier.padding(
                horizontal = Dimens.paddingThreeQuarters,
                vertical = Dimens.paddingHalf
            ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            menu.icon?.let {
                Icon(
                    imageVector = it,
                    contentDescription = menu.text,
                    modifier = Modifier.size(Dimens.paddingNormal)
                )
                Spacer(modifier = Modifier.padding(horizontal = padding.value))
            }
            AnimatedVisibility(
                visible = drawerValue == DrawerValue.Open,
                modifier = Modifier.height(Dimens.navDrawerItemHeight)
            ) {
                Text(
                    text = menu.text,
                    maxLines = 1,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}
