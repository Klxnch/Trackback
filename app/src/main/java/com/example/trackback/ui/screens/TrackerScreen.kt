package com.example.trackback.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Assignment
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.RadioButtonUnchecked
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.trackback.ui.components.BottomNavigationBar
import com.example.trackback.ui.theme.*

@Composable
fun TrackerScreen(navController: NavHostController, domain: String) {
    val isBusiness = domain == "business"
    
    val initialTasks = when(domain) {
        "business" -> listOf(
            "Finalize Q4 Projections" to true,
            "Approve Marketing Budget" to true,
            "Client Acquisition Sync" to false,
            "Infrastructure Scaling" to false,
            "Investor Relation Update" to false
        )
        "fitness" -> listOf(
            "Morning Cardio" to true,
            "Hit Protein Goal" to true,
            "Main Lift Session" to true,
            "Mobility/Stretching" to false,
            "Sleep Quality Log" to false
        )
        else -> listOf(
            "Morning Backtest" to true,
            "Journaled Trades" to true,
            "No FOMO Trades" to true,
            "Read for 30 mins" to false,
            "Gym Session" to false
        )
    }

    var tasks by remember { mutableStateOf(initialTasks) }

    val completedCount = tasks.count { it.second }
    val progress = if (tasks.isNotEmpty()) (completedCount.toFloat() / tasks.size.toFloat()) * 100 else 0f

    val primaryColor = MaterialTheme.colorScheme.primary
    val secondaryColor = MaterialTheme.colorScheme.secondary

    Scaffold(
        bottomBar = { BottomNavigationBar(navController, domain) },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /* Add task logic */ }, 
                containerColor = primaryColor,
                shape = RoundedCornerShape(16.dp)
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add Item", tint = Color.White)
            }
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 20.dp)
        ) {
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = if (isBusiness) "Strategic Planner" else "${domain.replaceFirstChar { it.uppercase() }} Tracker", 
                fontSize = 28.sp, 
                fontWeight = FontWeight.Black, 
                color = primaryColor
            )
            Text(
                text = if (isBusiness) "Manage your enterprise objectives." else "Log your discipline daily.", 
                color = Color.Gray, 
                fontSize = 14.sp
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Professional Progress Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = SurfaceGrey),
                shape = RoundedCornerShape(24.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(modifier = Modifier.padding(24.dp)) {
                    Text(
                        text = if (isBusiness) "Quarterly Target Progress" else "Today's Discipline", 
                        color = Color.Gray, 
                        fontSize = 14.sp
                    )
                    Row(
                        verticalAlignment = Alignment.Bottom,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            "${progress.toInt()}%", 
                            fontSize = 48.sp, 
                            fontWeight = FontWeight.Black, 
                            color = secondaryColor
                        )
                        Text(
                            text = if (isBusiness) "ON TRACK" else "CONSISTENT", 
                            fontSize = 12.sp, 
                            fontWeight = FontWeight.Bold, 
                            color = SuccessGreen,
                            modifier = Modifier.padding(bottom = 12.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    LinearProgressIndicator(
                        progress = { progress / 100f },
                        modifier = Modifier.fillMaxWidth().height(10.dp),
                        color = secondaryColor,
                        trackColor = Color.White.copy(alpha = 0.05f),
                        strokeCap = androidx.compose.ui.graphics.StrokeCap.Round
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = if (isBusiness) "Critical Objectives" else "Action Items", 
                    color = Color.White, 
                    fontSize = 20.sp, 
                    fontWeight = FontWeight.ExtraBold
                )
                if (isBusiness) {
                    Icon(Icons.AutoMirrored.Filled.Assignment, contentDescription = null, tint = primaryColor, modifier = Modifier.size(20.dp))
                }
            }
            
            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(bottom = 24.dp)
            ) {
                itemsIndexed(tasks) { index, (task, completed) ->
                    TaskItem(task, completed, isBusiness) {
                        val newList = tasks.toMutableList()
                        newList[index] = task to !completed
                        tasks = newList
                    }
                }
            }
        }
    }
}

@Composable
fun TaskItem(task: String, completed: Boolean, isBusiness: Boolean, onToggle: () -> Unit) {
    Surface(
        onClick = onToggle,
        color = SurfaceGrey,
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .padding(20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.weight(1f)) {
                Box(
                    modifier = Modifier
                        .size(24.dp)
                        .background(
                            if (completed) SuccessGreen.copy(alpha = 0.15f) else Color.White.copy(alpha = 0.05f),
                            CircleShape
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = if (completed) Icons.Default.CheckCircle else Icons.Default.RadioButtonUnchecked,
                        contentDescription = null,
                        tint = if (completed) SuccessGreen else Color.Gray,
                        modifier = Modifier.size(18.dp)
                    )
                }
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    task, 
                    color = if (completed) Color.White else Color.Gray,
                    fontWeight = if (completed) FontWeight.Bold else FontWeight.Medium,
                    fontSize = 15.sp
                )
            }
            if (isBusiness && !completed) {
                Text("HIGH PRIORITY", color = ErrorRed.copy(alpha = 0.7f), fontSize = 10.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}
