package com.example.trackback.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Payments
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.trackback.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PayoutsScreen(navController: NavHostController, domain: String) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Payouts", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = DarkGrey)
            )
        },
        containerColor = DarkGrey
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(vertical = 20.dp)
        ) {
            item {
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(24.dp),
                    color = FirmanagerSurface
                ) {
                    Column(modifier = Modifier.padding(24.dp)) {
                        Text("Available for Withdrawal", color = Color.Gray, fontSize = 13.sp)
                        Text("$84,250.00", color = Color.White, fontSize = 32.sp, fontWeight = FontWeight.Black)
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(
                            onClick = { },
                            modifier = Modifier.fillMaxWidth(),
                            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Text("Request Payout", fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }

            item {
                Text("Recent Payouts", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
            }

            items(payoutList) { payout ->
                PayoutItem(payout)
            }
        }
    }
}

@Composable
fun PayoutItem(payout: PayoutData) {
    Surface(
        color = SurfaceGrey,
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier.size(40.dp).background(SuccessGreen.copy(alpha = 0.1f), CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.Default.Payments, contentDescription = null, tint = SuccessGreen, modifier = Modifier.size(20.dp))
                }
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text(payout.date, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                    Text(payout.method, color = Color.Gray, fontSize = 12.sp)
                }
            }
            Column(horizontalAlignment = Alignment.End) {
                Text(payout.amount, color = Color.White, fontWeight = FontWeight.Black, fontSize = 15.sp)
                Text(payout.status, color = SuccessGreen, fontSize = 11.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}

data class PayoutData(val date: String, val amount: String, val method: String, val status: String)

val payoutList = listOf(
    PayoutData("Oct 24, 2023", "$12,400.00", "Bank Transfer", "Completed"),
    PayoutData("Oct 10, 2023", "$8,200.00", "USDT (ERC20)", "Completed"),
    PayoutData("Sep 25, 2023", "$15,000.00", "Bank Transfer", "Completed"),
    PayoutData("Sep 12, 2023", "$5,500.00", "PayPal", "Completed")
)
