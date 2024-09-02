@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)

package com.joeahkim.chapaake

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.with
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.joeahkim.chapaake.ads.BannerAdView
import com.joeahkim.chapaake.pages.HomePage
import com.joeahkim.chapaake.pages.NotificationPage
import com.joeahkim.chapaake.pages.Results

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    val navItems = listOf(
        NavItem("Home", Icons.Default.Home, 0),
        NavItem("Results", Icons.Default.List, 0),
        NavItem("T & C", Icons.Default.Info, 0),

        )

    var selectedIndex by remember { mutableStateOf(0) }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = { MyTopAppBar() },

        bottomBar = {

            Column {
                BannerAdView(adUnitId = "ca-app-pub-3940256099942544/6300978111")

                NavigationBar {
                    navItems.forEachIndexed { index, navItem ->
                        NavigationBarItem(
                            selected = selectedIndex == index,
                            onClick = { selectedIndex = index },
                            label = { Text(text = navItem.label) },
                            icon = {
                                BadgedBox(
                                    badge = {
                                        if (navItem.badgeCount > 0) {
                                            Badge {
                                                Text(text = navItem.badgeCount.toString())
                                            }
                                        }
                                    }
                                ) {
                                    Icon(imageVector = navItem.icon, contentDescription = "icon")
                                }
                            }
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize() // Ensures the content takes up the remaining space
        ) {
            ContentScreen(modifier = Modifier.fillMaxSize(), selectedIndex = selectedIndex)

        }

    }

}


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ContentScreen(
    modifier: Modifier = Modifier,
    selectedIndex: Int
) {
    AnimatedContent(
        targetState = selectedIndex,
        transitionSpec = {
            if (targetState > initialState) {
                slideInHorizontally(animationSpec = tween(500)) + fadeIn() with
                        slideOutHorizontally(animationSpec = tween(500)) + fadeOut()
            } else {
                slideInHorizontally(animationSpec = tween(500), initialOffsetX = { -it }) + fadeIn() with
                        slideOutHorizontally(animationSpec = tween(500), targetOffsetX = { it }) + fadeOut()
            }.using(SizeTransform(clip = false))
        }
    ) { targetState ->
        when (targetState) {
            0 -> HomePage()
            1 -> Results()
            2 -> NotificationPage()
            else -> HomePage()
        }
    }
}
