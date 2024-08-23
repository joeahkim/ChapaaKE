package com.joeahkim.chapaake

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.joeahkim.chapaake.ui.theme.ChapaaKETheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        Firebase.database.setPersistenceEnabled(true)
        val splashScreen = installSplashScreen()
        setContent {
            ChapaaKETheme {
                MainScreen()
                }
            }
        }
    }

