package com.example.trackback

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.trackback.navigation.GhostMentorNavGraph
import com.example.trackback.ui.theme.TrackbackTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        // Handle the splash screen transition.
        installSplashScreen()
        
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            
            // Extract domain from current route arguments
            val domain = navBackStackEntry?.arguments?.getString("domain") ?: "forex"

            TrackbackTheme(domain = domain) {
                GhostMentorNavGraph(navController = navController)
            }
        }
    }
}
