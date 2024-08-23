package com.joeahkim.chapaake

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.joeahkim.chapaake.ui.theme.GreenJC

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopAppBar() {
    TopAppBar(
        title = { Text("Ronaldo Betting Tips") },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = GreenJC,
            titleContentColor = Color.White
        )
    )
}
