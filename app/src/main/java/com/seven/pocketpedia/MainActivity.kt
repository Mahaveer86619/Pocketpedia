package com.seven.pocketpedia

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.seven.pocketpedia.presentation.screens.LandingScreen
import com.seven.pocketpedia.ui.theme.PocketpediaTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PocketpediaTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    BarColor()
                    LandingScreen()
                }
            }
        }
    }

    @Composable
    private fun BarColor() {

        val systemUiController = rememberSystemUiController()

        val color = MaterialTheme.colorScheme.background
        
        LaunchedEffect(color) {
            systemUiController.setNavigationBarColor(color)
            systemUiController.setStatusBarColor(color)
            systemUiController.setStatusBarColor(color)
        }
        
    }

}

