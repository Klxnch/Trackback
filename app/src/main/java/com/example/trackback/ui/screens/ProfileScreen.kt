package com.example.trackback.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.trackback.navigation.Screen
import com.example.trackback.ui.components.BottomNavigationBar
import com.example.trackback.ui.theme.*

@Composable
fun ProfileScreen(navController: NavHostController, domain: String) {
    val name = when(domain) {
        "business" -> "Venture Lead"
        "fitness" -> "Elite Athlete"
        else -> "Ghost Trader"
    }

    val rank = when(domain) {
        "business" -> "Series A Founder"
        "fitness" -> "Pro Tier"
        else -> "Intermediate Level"
    }

    val stats = when(domain) {
        "business" -> listOf("Efficiency" to "92%", "Runway" to "18m", "ROI" to "+24%")
        "fitness" -> listOf("V02 Max" to "58", "Power" to "320w", "Fat %" to "12%")
        else -> listOf("Consistency" to "94%", "Streak" to "21d", "Growth" to "+12%")
    }

    Scaffold(
        bottomBar = { BottomNavigationBar(navController, domain) },
        containerColor = DarkGrey
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(40.dp))
            Text(
                "Profile", 
                fontSize = 32.sp, 
                fontWeight = FontWeight.ExtraBold, 
                color = GhostPurple
            )
            
            Spacer(modifier = Modifier.height(32.dp))

            // Profile Picture with Gradient Border
            Box(
                modifier = Modifier
                    .size(128.dp)
                    .background(
                        Brush.linearGradient(listOf(GhostPurple, NeonBlue)),
                        CircleShape
                    )
                    .padding(4.dp)
                    .background(DarkGrey, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(4.dp)
                        .clip(CircleShape)
                        .background(SurfaceGrey),
                    contentAlignment = Alignment.Center
                ) {
                    val initials = when(domain) {
                        "business" -> "VL"
                        "fitness" -> "EA"
                        else -> "GT"
                    }
                    Text(initials, fontSize = 48.sp, fontWeight = FontWeight.Black, color = GhostPurple)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(name, fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color.White)
            Text(rank, color = NeonBlue, fontSize = 14.sp, fontWeight = FontWeight.Medium)

            Spacer(modifier = Modifier.height(32.dp))

            // Stats Row
            Row(
                modifier = Modifier.fillMaxWidth(), 
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                StatItem(stats[0].first, stats[0].second)
                VerticalDivider(modifier = Modifier.height(30.dp), color = Color.White.copy(alpha = 0.1f))
                StatItem(stats[1].first, stats[1].second)
                VerticalDivider(modifier = Modifier.height(30.dp), color = Color.White.copy(alpha = 0.1f))
                StatItem(stats[2].first, stats[2].second)
            }

            Spacer(modifier = Modifier.height(40.dp))

            // Settings Section
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    "Settings", 
                    color = Color.Gray, 
                    fontSize = 12.sp, 
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 8.dp, bottom = 8.dp)
                )
                ProfileOption("Personal Information", Icons.Default.Settings)
                ProfileOption("Security & Privacy", Icons.Default.Security)
                ProfileOption("Notifications", Icons.Default.Notifications)
                ProfileOption("Preferences", Icons.Default.Edit)
            }
            
            Spacer(modifier = Modifier.weight(1f))
            
            TextButton(
                onClick = { 
                    navController.navigate(Screen.Onboarding.route) {
                        popUpTo(0) { inclusive = true }
                    }
                },
                modifier = Modifier.padding(bottom = 16.dp)
            ) {
                Text("LOGOUT", color = ErrorRed, fontWeight = FontWeight.Bold, letterSpacing = 1.sp)
            }
        }
    }
}

@Composable
fun StatItem(label: String, value: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(value, fontSize = 22.sp, fontWeight = FontWeight.Black, color = Color.White)
        Text(label, fontSize = 12.sp, color = Color.Gray)
    }
}

@Composable
fun ProfileOption(title: String, icon: androidx.compose.ui.graphics.vector.ImageVector) {
    Surface(
        onClick = { },
        color = SurfaceGrey,
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(GhostPurple.copy(alpha = 0.1f), RoundedCornerShape(10.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(icon, contentDescription = null, tint = GhostPurple, modifier = Modifier.size(20.dp))
            }
            Spacer(modifier = Modifier.width(16.dp))
            Text(title, color = Color.White, modifier = Modifier.weight(1f), fontSize = 15.sp)
            Icon(Icons.Default.ChevronRight, contentDescription = null, tint = Color.Gray)
        }
    }
}
