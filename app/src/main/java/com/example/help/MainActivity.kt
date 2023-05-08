package com.example.help

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsTopHeight
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.core.view.WindowCompat
import com.example.help.ui.login.LoginView
import com.example.help.ui.theme.ColorPallet
import com.example.help.ui.theme.HelpMaterial3Theme
import com.example.help.ui.theme.graySurface
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.insets.statusBarsHeight
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.guru.fontawesomecomposelib.FaIcon
import com.guru.fontawesomecomposelib.FaIcons
import timber.log.Timber

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //沉浸式状态栏
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            rememberSystemUiController().setStatusBarColor(
                Color.Transparent,
                darkIcons = MaterialTheme.colors.isLight
            )
            HelpMaterial3Theme(
                colorPallet = ColorPallet.BLUE
            ) {
                Column {
                    Spacer(
                        modifier = Modifier
                            .windowInsetsTopHeight(WindowInsets.statusBars)
                            .fillMaxWidth()
                    )
//                    AppContent()
                    LoginView()
                }
            }
        }
    }
}

/**
 * 界面主体
 */
@Composable
fun AppContent() {
    //默认为HOME图标
    val navItemState = rememberSaveable {
        mutableStateOf(BottomNavType.HOME)
    }
    Scaffold(bottomBar = {
        BottomNavigation(navItemState)
    }, content = { paddingValues ->
        AppContentBody(
            navTypeState = navItemState.value,
            modifier = Modifier.padding(paddingValues)
        )
    }
    )
}

enum class BottomNavType {
    HOME,
    CATEGORY,
    CUSTOMER,
}

/**
 * 导航栏
 */
@Composable
fun BottomNavigation(
    navTypeState: MutableState<BottomNavType>
) {
    val bottomNavBackground =
        if (isSystemInDarkTheme()) graySurface else MaterialTheme.colors.background
    androidx.compose.material.BottomNavigation(backgroundColor = bottomNavBackground) {
        BottomNavigationItem(
            icon = {
                FaIcon(
                    faIcon = FaIcons.Home,
                    tint = LocalContentColor.current.copy(alpha = LocalContentAlpha.current)
                )
            },
            selected = navTypeState.value == BottomNavType.HOME,
            onClick = { navTypeState.value = BottomNavType.HOME },
            label = {
                Text(text = stringResource(id = R.string.navigation_item_home))
            },
        )
        BottomNavigationItem(
            icon = {
                FaIcon(
                    faIcon = FaIcons.List,
                    tint = LocalContentColor.current.copy(alpha = LocalContentAlpha.current)
                )
            },
            selected = navTypeState.value == BottomNavType.CATEGORY,
            onClick = { navTypeState.value = BottomNavType.CATEGORY },
            label = {
                Text(text = stringResource(id = R.string.navigation_item_category))
            },
        )
        BottomNavigationItem(
            icon = {
                FaIcon(
                    faIcon = FaIcons.User,
                    tint = LocalContentColor.current.copy(alpha = LocalContentAlpha.current)
                )
            },
            selected = navTypeState.value == BottomNavType.CUSTOMER,
            onClick = { navTypeState.value = BottomNavType.CUSTOMER },
            label = {
                Text(text = stringResource(id = R.string.navigation_item_customer))
            },
        )
    }
}

/**
 * 内容体
 */
@Composable
fun AppContentBody(
    navTypeState: BottomNavType,
    modifier: Modifier = Modifier
) {
    Crossfade(targetState = navTypeState, modifier = modifier) { navType ->
        when (navType) {
            BottomNavType.HOME -> Timber.d("CATEGORY")
            BottomNavType.CATEGORY -> Timber.d("CATEGORY")
            BottomNavType.CUSTOMER -> Timber.d("CUSTOMER")
        }
    }
}
