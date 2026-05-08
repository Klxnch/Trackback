package com.example.trackback.ui.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.trackback.navigation.Screen
import com.example.trackback.ui.components.BottomNavigationBar
import com.example.trackback.ui.theme.*

@Composable
fun HomeScreen(navController: NavHostController, domain: String) {
    val isBusiness = domain == "business"
    
    val title = when(domain) {
        "business" -> "Firmanager"
        "fitness" -> "Elite Athlete"
        else -> "Ghost Trader"
    }

    val primaryColor = MaterialTheme.colorScheme.primary
    val secondaryColor = MaterialTheme.colorScheme.secondary

    Scaffold(
        bottomBar = { BottomNavigationBar(navController, domain) },
        containerColor = MaterialTheme.colorScheme.background
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 20.dp)
        ) {
            Spacer(modifier = Modifier.height(24.dp))
            
            // Premium Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = if (isBusiness) "Unified Dashboard" else "Welcome back,",
                        color = Color.Gray,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium
                    )
                    Text(
                        text = title,
                        color = Color.White,
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Black
                    )
                }
                IconButton(
                    onClick = { navController.navigate(Screen.Profile.createRoute(domain)) },
                    modifier = Modifier.size(48.dp).background(SurfaceGrey, CircleShape)
                ) {
                    Icon(Icons.Default.Person, contentDescription = null, tint = primaryColor)
                }
            }

            Spacer(modifier = Modifier.height(28.dp))

            if (isBusiness) {
                FirmanagerDashboard(primaryColor, secondaryColor, navController, domain)
            } else {
                DefaultDashboard(domain, primaryColor, secondaryColor, navController)
            }
        }
    }
}

@Composable
fun FirmanagerDashboard(primaryColor: Color, secondaryColor: Color, navController: NavHostController, domain: String) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(24.dp),
        contentPadding = PaddingValues(bottom = 24.dp)
    ) {
        item {
            // High-Performance Balance Card
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(32.dp),
                color = FirmanagerSurface,
                shadowElevation = 12.dp
            ) {
                Column(modifier = Modifier.padding(28.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text("Total Portfolio Value", color = Color.Gray, fontSize = 13.sp)
                            Text("$412,890.00", color = Color.White, fontSize = 36.sp, fontWeight = FontWeight.Black)
                        }
                        Surface(
                            color = SuccessGreen.copy(alpha = 0.15f),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(Icons.Default.ArrowUpward, contentDescription = null, tint = SuccessGreen, modifier = Modifier.size(14.dp))
                                Text("+12.5%", color = SuccessGreen, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                            }
                        }
                    }
                    
                    Spacer(modifier = Modifier.height(24.dp))
                    
                    // Simple Performance Chart (Simulated)
                    PerformanceChart(color = secondaryColor)
                    
                    Spacer(modifier = Modifier.height(24.dp))
                    
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        FinanceStatItem("Revenue", "+$42.3k", FirmanagerGreen)
                        FinanceStatItem("Burn Rate", "-$12.1k", ErrorRed)
                        FinanceStatItem("Runway", "18m", FirmanagerPrimary)
                    }
                }
            }
        }

        item {
            Text("Management Console", color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.ExtraBold)
            Spacer(modifier = Modifier.height(16.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                FirmanagerAction("Payouts", Icons.Default.Payments, secondaryColor, Modifier.weight(1f)) {
                    navController.navigate(Screen.Payouts.createRoute(domain))
                }
                FirmanagerAction("Clients", Icons.Default.Groups, primaryColor, Modifier.weight(1f)) {
                    navController.navigate(Screen.Clients.createRoute(domain))
                }
                FirmanagerAction("Vault", Icons.Default.Lock, Color(0xFFAC92ED), Modifier.weight(1f)) {
                    navController.navigate(Screen.Vault.createRoute(domain))
                }
            }
        }

        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Enterprise Feed", color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.ExtraBold)
                TextButton(onClick = { navController.navigate(Screen.AuditLog.createRoute(domain)) }) {
                    Text("Audit Log", color = secondaryColor)
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                FirmTransactionItem("Global Cloud Ops", "-$4,200.00", "Infrastructure", ErrorRed)
                FirmTransactionItem("Venture Capital Infusion", "+$150,000.00", "Funding", SuccessGreen)
                FirmTransactionItem("Sales Commission", "-$2,100.00", "Operations", ErrorRed)
                FirmTransactionItem("Enterprise License", "+$12,500.00", "Revenue", SuccessGreen)
            }
        }
    }
}

@Composable
fun PerformanceChart(color: Color) {
    Canvas(modifier = Modifier.fillMaxWidth().height(80.dp)) {
        val points = listOf(0.8f, 0.6f, 0.9f, 0.4f, 0.7f, 0.5f, 1.0f)
        val path = Path()
        val stepX = size.width / (points.size - 1)
        
        points.forEachIndexed { index, point ->
            val x = index * stepX
            val y = size.height - (point * size.height)
            if (index == 0) path.moveTo(x, y) else path.lineTo(x, y)
        }
        
        drawPath(
            path = path,
            color = color,
            style = Stroke(width = 3.dp.toPx())
        )
    }
}

@Composable
fun FirmanagerAction(label: String, icon: androidx.compose.ui.graphics.vector.ImageVector, color: Color, modifier: Modifier = Modifier, onClick: () -> Unit) {
    Surface(
        modifier = modifier.height(100.dp),
        shape = RoundedCornerShape(24.dp),
        color = SurfaceGrey,
        onClick = onClick
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(icon, contentDescription = null, tint = color, modifier = Modifier.size(28.dp))
            Spacer(modifier = Modifier.height(8.dp))
            Text(label, color = Color.White, fontSize = 13.sp, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun FirmTransactionItem(title: String, amount: String, category: String, color: Color) {
    Surface(
        color = SurfaceGrey,
        shape = RoundedCornerShape(24.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(18.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier.size(46.dp).background(color.copy(alpha = 0.1f), CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        if (amount.startsWith("+")) Icons.Default.Add else Icons.Default.Remove,
                        contentDescription = null,
                        tint = color,
                        modifier = Modifier.size(22.dp)
                    )
                }
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(title, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 15.sp)
                    Text(category, color = Color.Gray, fontSize = 12.sp)
                }
            }
            Text(amount, color = color, fontWeight = FontWeight.Black, fontSize = 16.sp)
        }
    }
}

@Composable
fun FinanceStatItem(label: String, value: String, color: Color) {
    Column {
        Text(label, color = Color.Gray, fontSize = 11.sp, fontWeight = FontWeight.Bold)
        Text(value, color = color, fontSize = 15.sp, fontWeight = FontWeight.Black)
    }
}

@Composable
fun DefaultDashboard(domain: String, primaryColor: Color, secondaryColor: Color, navController: NavHostController) {
    val headerImage = when(domain) {
        "fitness" -> "https://images.unsplash.com/photo-1517836357463-d25dfeac3438?q=80&w=500"
        else -> "https://images.unsplash.com/photo-1611974714020-1dc77a672322?q=80&w=500"
    }
    
    val mainValue = if (domain == "fitness") "12" else "85"
    val mainMetric = if (domain == "fitness") "Body Fat %" else "Discipline Score"

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Card(
            modifier = Modifier.fillMaxWidth().height(160.dp),
            shape = RoundedCornerShape(32.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {
            Box {
                AsyncImage(
                    model = headerImage,
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop,
                    alpha = 0.7f
                )
                Box(modifier = Modifier.fillMaxSize().background(Brush.verticalGradient(listOf(Color.Transparent, Color.Black.copy(alpha = 0.9f)))))
                Text("THE VOID IS WATCHING", modifier = Modifier.align(Alignment.BottomStart).padding(20.dp), color = Color.White, fontWeight = FontWeight.Black, letterSpacing = 2.sp, fontSize = 12.sp)
            }
        }

        Spacer(modifier = Modifier.height(40.dp))

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(220.dp)
                .background(Brush.sweepGradient(listOf(primaryColor, secondaryColor, primaryColor)), shape = CircleShape)
                .padding(12.dp)
                .background(MaterialTheme.colorScheme.background, shape = CircleShape)
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(mainValue, fontSize = 64.sp, fontWeight = FontWeight.Black, color = secondaryColor)
                Text(mainMetric, fontSize = 14.sp, color = Color.Gray)
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = { navController.navigate(Screen.Chat.createRoute(domain)) },
            modifier = Modifier.fillMaxWidth().height(64.dp),
            colors = ButtonDefaults.buttonColors(containerColor = primaryColor),
            shape = RoundedCornerShape(24.dp)
        ) {
            Icon(Icons.Default.AutoAwesome, contentDescription = null)
            Spacer(modifier = Modifier.width(12.dp))
            Text("CONSULT GHOST MENTOR", fontSize = 16.sp, fontWeight = FontWeight.ExtraBold)
        }
    }
}
