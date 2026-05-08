package com.example.trackback.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.History
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
fun AuditLogScreen(navController: NavHostController, domain: String) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Audit Log", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Color.White)
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
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(top = 20.dp, bottom = 24.dp)
        ) {
            items(auditLogs) { log ->
                AuditLogItem(log)
            }
        }
    }
}

@Composable
fun AuditLogItem(log: AuditLogData) {
    Surface(
        color = SurfaceGrey,
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier.size(40.dp).background(Color.Gray.copy(alpha = 0.1f), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Default.History, contentDescription = null, tint = Color.Gray, modifier = Modifier.size(20.dp))
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(log.action, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                Text("${log.user} • ${log.time}", color = Color.Gray, fontSize = 12.sp)
            }
        }
    }
}

data class AuditLogData(val action: String, val user: String, val time: String)

val auditLogs = listOf(
    AuditLogData("Updated Client Retainer", "Admin", "2m ago"),
    AuditLogData("Payout Requested", "Finance", "1h ago"),
    AuditLogData("Vault Access: API Keys", "System", "3h ago"),
    AuditLogData("New Client Onboarded", "Sales", "5h ago"),
    AuditLogData("Report Generated", "Admin", "Yesterday"),
    AuditLogData("Database Backup", "System", "Yesterday")
)
