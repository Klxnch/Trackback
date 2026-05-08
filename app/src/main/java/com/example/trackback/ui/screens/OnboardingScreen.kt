package com.example.trackback.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.trackback.ui.theme.*

@Composable
fun OnboardingScreen(onNavigateToHome: (String) -> Unit) {
    var goal by remember { mutableStateOf("Business") }
    var experience by remember { mutableStateOf("Intermediate") }

    val primaryColor = if (goal == "Business") FirmanagerPrimary else GhostPurple
    val secondaryColor = if (goal == "Business") FirmanagerTeal else NeonBlue

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF010101))
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Spacer(modifier = Modifier.height(80.dp))
            Text(
                text = if (goal == "Business") "FIRMANAGER" else "GHOST MENTOR",
                fontSize = 44.sp,
                fontWeight = FontWeight.Black,
                style = TextStyle(
                    brush = Brush.horizontalGradient(listOf(primaryColor, secondaryColor))
                ),
                letterSpacing = (-1).sp
            )
            Text(
                text = if (goal == "Business") "Enterprise-grade financial management." else "Master your discipline in the void.",
                color = Color.Gray,
                fontSize = 15.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
        
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                "Select Intelligence Domain", 
                color = Color.White, 
                fontSize = 18.sp, 
                fontWeight = FontWeight.ExtraBold,
                modifier = Modifier.padding(bottom = 20.dp)
            )
            
            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                GoalOption("Business", goal == "Business", Modifier.weight(1f)) { goal = "Business" }
                GoalOption("Forex", goal == "Forex", Modifier.weight(1f)) { goal = "Forex" }
                GoalOption("Fitness", goal == "Fitness", Modifier.weight(1f)) { goal = "Fitness" }
            }
            
            Spacer(modifier = Modifier.height(40.dp))
            
            Text(
                "Experience & Tier", 
                color = Color.White, 
                fontSize = 18.sp, 
                fontWeight = FontWeight.ExtraBold,
                modifier = Modifier.padding(bottom = 20.dp)
            )
            
            ExperienceOption("Beginner", experience == "Beginner", primaryColor) { experience = "Beginner" }
            ExperienceOption("Intermediate", experience == "Intermediate", primaryColor) { experience = "Intermediate" }
            ExperienceOption("Advanced", experience == "Advanced", primaryColor) { experience = "Advanced" }
        }
        
        Button(
            onClick = { onNavigateToHome(goal.lowercase()) },
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .padding(bottom = 8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = primaryColor),
            shape = RoundedCornerShape(16.dp),
            elevation = ButtonDefaults.buttonElevation(defaultElevation = 12.dp)
        ) {
            Text(
                text = if (goal == "Business") "LAUNCH CONSOLE" else "ENTER THE VOID", 
                fontSize = 18.sp, 
                fontWeight = FontWeight.Black, 
                letterSpacing = 1.sp
            )
        }
    }
}

@Composable
fun GoalOption(text: String, isSelected: Boolean, modifier: Modifier = Modifier, onSelect: () -> Unit) {
    val color = when(text) {
        "Business" -> FirmanagerPrimary
        "Forex" -> GhostPurple
        else -> SunsetOrange
    }
    
    Surface(
        onClick = onSelect,
        modifier = modifier.height(64.dp),
        shape = RoundedCornerShape(16.dp),
        color = if (isSelected) color.copy(alpha = 0.15f) else Color(0xFF1C1C1E),
        border = if (isSelected) androidx.compose.foundation.BorderStroke(2.dp, color) else null
    ) {
        Box(contentAlignment = Alignment.Center) {
            Text(
                text = text, 
                color = if (isSelected) color else Color.Gray, 
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp
            )
        }
    }
}

@Composable
fun ExperienceOption(text: String, isSelected: Boolean, activeColor: Color, onSelect: () -> Unit) {
    Surface(
        onClick = onSelect,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .height(58.dp),
        shape = RoundedCornerShape(16.dp),
        color = if (isSelected) activeColor.copy(alpha = 0.1f) else Color(0xFF1C1C1E),
        border = if (isSelected) androidx.compose.foundation.BorderStroke(1.dp, activeColor.copy(alpha = 0.5f)) else null
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = text, 
                color = if (isSelected) Color.White else Color.Gray, 
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp
            )
            RadioButton(
                selected = isSelected,
                onClick = null,
                colors = RadioButtonDefaults.colors(
                    selectedColor = activeColor, 
                    unselectedColor = Color.DarkGray
                )
            )
        }
    }
}
