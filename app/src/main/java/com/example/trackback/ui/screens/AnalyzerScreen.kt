package com.example.trackback.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.navigation.NavHostController
import com.example.trackback.ui.components.BottomNavigationBar
import com.example.trackback.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnalyzerScreen(navController: NavHostController, domain: String) {
    var field1 by remember { mutableStateOf("") }
    var field2 by remember { mutableStateOf("") }
    var field3 by remember { mutableStateOf("") }
    var field4 by remember { mutableStateOf("") }
    var note by remember { mutableStateOf("") }
    var showResult by remember { mutableStateOf(false) }

    val primaryColor = MaterialTheme.colorScheme.primary
    val secondaryColor = MaterialTheme.colorScheme.secondary
    val isBusiness = domain == "business"

    val labels = when(domain) {
        "business" -> listOf("Monthly Revenue", "Operational Costs", "New Customers", "Churn Rate", "Financial Strategy")
        "fitness" -> listOf("Workout Type", "Calories Consumed", "Protein (g)", "Water Intake (L)", "Body Composition Notes")
        else -> listOf("Pair Traded", "Lot Size", "Time and Session", "Outcome (P/L)", "Reason for Entry")
    }

    val placeholders = when(domain) {
        "business" -> listOf("e.g. $15,000", "e.g. $5,200", "e.g. 120", "e.g. 2.5%", "What is the primary objective?")
        "fitness" -> listOf("e.g. Push Day", "e.g. 2400", "e.g. 180g", "e.g. 3.5L", "How did the body feel?")
        else -> listOf("e.g. EUR/USD", "e.g. 0.05", "e.g. London Open", "e.g. +$240", "Why did you enter this trade?")
    }

    Scaffold(
        bottomBar = { BottomNavigationBar(navController, domain) },
        containerColor = MaterialTheme.colorScheme.background
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 20.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = if (isBusiness) "Financial Auditor" else "Performance Analyzer",
                fontSize = 28.sp,
                fontWeight = FontWeight.Black,
                style = TextStyle(
                    brush = Brush.horizontalGradient(listOf(primaryColor, secondaryColor))
                )
            )
            Text(
                text = if (isBusiness) "Verify your business metrics." else "The Ghost is auditing your performance.", 
                color = Color.Gray, 
                fontSize = 14.sp
            )

            Spacer(modifier = Modifier.height(32.dp))

            AnalyzerField(labels[0], field1, placeholder = placeholders[0]) { field1 = it }
            AnalyzerField(labels[1], field2, placeholder = placeholders[1]) { field2 = it }
            AnalyzerField(labels[2], field3, placeholder = placeholders[2]) { field3 = it }
            AnalyzerField(labels[3], field4, placeholder = placeholders[3]) { field4 = it }
            AnalyzerField(labels[4], note, minLines = 3, placeholder = placeholders[4]) { note = it }

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = { showResult = true },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                colors = ButtonDefaults.buttonColors(containerColor = primaryColor),
                shape = RoundedCornerShape(16.dp),
                elevation = ButtonDefaults.buttonElevation(8.dp)
            ) {
                Text(
                    text = if (isBusiness) "GENERATE AUDIT" else "RUN ANALYSIS", 
                    fontWeight = FontWeight.Bold, 
                    fontSize = 16.sp, 
                    letterSpacing = 1.sp
                )
            }

            if (showResult) {
                Spacer(modifier = Modifier.height(32.dp))
                TradeAnalysisResult(domain)
                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    }
}

@Composable
fun AnalyzerField(
    label: String, 
    value: String, 
    minLines: Int = 1, 
    placeholder: String = "",
    onValueChange: (String) -> Unit
) {
    Column(modifier = Modifier.padding(vertical = 10.dp)) {
        Text(label, color = Color.White.copy(alpha = 0.8f), fontSize = 14.sp, fontWeight = FontWeight.Medium, modifier = Modifier.padding(bottom = 6.dp))
        TextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            minLines = minLines,
            placeholder = { Text(placeholder, color = Color.Gray) },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = SurfaceGrey,
                unfocusedContainerColor = SurfaceGrey,
                focusedIndicatorColor = MaterialTheme.colorScheme.secondary,
                unfocusedIndicatorColor = Color.Transparent,
                cursorColor = MaterialTheme.colorScheme.secondary,
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White
            ),
            shape = RoundedCornerShape(16.dp)
        )
    }
}

@Composable
fun TradeAnalysisResult(domain: String) {
    val reportTitle = when(domain) {
        "business" -> "FINANCIAL REPORT"
        "fitness" -> "PHYSIQUE REPORT"
        else -> "TRADING REPORT"
    }

    val ghostTake = when(domain) {
        "business" -> "Firmanager Take: Your burn rate is optimal for this phase. Focus on increasing the LTV of current clients."
        "fitness" -> "Ghost's Take: Your hydration levels are sub-par. Recovery will be slow if you don't hit 4L daily."
        else -> "Ghost's Take: You entered without a clear displacement. Next time, wait for the MSS before going heavy on lots."
    }

    val secondaryColor = MaterialTheme.colorScheme.secondary

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = SurfaceGrey),
        shape = RoundedCornerShape(24.dp),
        elevation = CardDefaults.cardElevation(12.dp)
    ) {
        Column(modifier = Modifier.padding(24.dp)) {
            Text(reportTitle, fontWeight = FontWeight.ExtraBold, color = secondaryColor, letterSpacing = 1.sp)
            HorizontalDivider(modifier = Modifier.padding(vertical = 16.dp), color = Color.White.copy(alpha = 0.1f))
            
            when(domain) {
                "business" -> {
                    ResultRow("Margin", "Healthy (28%)", SuccessGreen)
                    ResultRow("Growth", "Target Met", SuccessGreen)
                    ResultRow("Verdict", "Scale Operations", FirmanagerPrimary)
                }
                "fitness" -> {
                    ResultRow("Intensity", "High", SuccessGreen)
                    ResultRow("Nutrition", "Off-track", ErrorRed)
                    ResultRow("Verdict", "Increase Protein", MaterialTheme.colorScheme.primary)
                }
                else -> {
                    ResultRow("Risk Level", "Medium", WarningOrange)
                    ResultRow("Mistakes", "FOMO entry", ErrorRed)
                    ResultRow("Verdict", "Wait for HTF", SuccessGreen)
                }
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Text(
                ghostTake,
                color = Color.Gray,
                fontSize = 13.sp,
                lineHeight = 18.sp
            )
        }
    }
}

@Composable
fun ResultRow(label: String, value: String, color: Color) {
    Row(modifier = Modifier.padding(vertical = 6.dp), verticalAlignment = Alignment.CenterVertically) {
        Box(modifier = Modifier.size(8.dp).background(color, CircleShape))
        Spacer(modifier = Modifier.width(12.dp))
        Text("$label: ", color = Color.Gray, fontSize = 14.sp)
        Text(value, color = color, fontWeight = FontWeight.Bold, fontSize = 14.sp)
    }
}
