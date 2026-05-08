package com.example.trackback.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Assignment
import androidx.compose.material.icons.filled.Analytics
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.trackback.navigation.Screen
import com.example.trackback.ui.theme.SurfaceGrey

@Composable
fun BottomNavigationBar(navController: NavHostController, domain: String) {
    val isBusiness = domain == "business"
    
    NavigationBar(
        containerColor = SurfaceGrey,
        tonalElevation = 8.dp
    ) {
        val currentRoute = navController.currentBackStackEntry?.destination?.route
        
        val items = listOf(
            NavigationItem(
                if (isBusiness) "Dashboard" else "Home", 
                Screen.Home.createRoute(domain), 
                Icons.Default.Home, 
                Screen.Home.route
            ),
            NavigationItem(
                if (isBusiness) "Audit" else "Analyzer", 
                Screen.Analyzer.createRoute(domain), 
                Icons.Default.Analytics, 
                Screen.Analyzer.route
            ),
            NavigationItem(
                if (isBusiness) "Strategy" else "Tracker", 
                Screen.Tracker.createRoute(domain), 
                Icons.AutoMirrored.Filled.Assignment,
                Screen.Tracker.route
            ),
            NavigationItem(
                "Profile", 
                Screen.Profile.createRoute(domain), 
                Icons.Default.Person, 
                Screen.Profile.route
            )
        )

        items.forEach { item ->
            val selected = currentRoute == item.routeTemplate
            NavigationBarItem(
                selected = selected,
                onClick = {
                    if (currentRoute != item.routeTemplate) {
                        navController.navigate(item.route) {
                            popUpTo(Screen.Home.createRoute(domain)) { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                },
                icon = { 
                    Icon(
                        item.icon, 
                        contentDescription = item.label,
                        modifier = Modifier.size(24.dp)
                    ) 
                },
                label = { 
                    Text(
                        item.label, 
                        fontSize = 10.sp,
                        color = if (selected) Color.White else Color.Gray
                    ) 
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.White,
                    unselectedIconColor = Color.Gray,
                    indicatorColor = Color.White.copy(alpha = 0.1f)
                )
            )
        }
    }
}

data class NavigationItem(
    val label: String,
    val route: String,
    val icon: androidx.compose.ui.graphics.vector.ImageVector,
    val routeTemplate: String
)
