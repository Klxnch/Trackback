package com.example.trackback.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.Search
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
fun ClientsScreen(navController: NavHostController, domain: String) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Clients", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Color.White)
                    }
                },
                actions = {
                    IconButton(onClick = { }) {
                        Icon(Icons.Default.Search, contentDescription = "Search", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = DarkGrey)
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { },
                containerColor = FirmanagerPrimary,
                contentColor = Color.White
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add Client")
            }
        },
        containerColor = DarkGrey
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(top = 20.dp, bottom = 80.dp)
        ) {
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Active Retainers", color = Color.Gray, fontSize = 14.sp)
                    Text("12 Total", color = FirmanagerPrimary, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                }
            }

            items(clientList) { client ->
                ClientItem(client)
            }
        }
    }
}

@Composable
fun ClientItem(client: ClientData) {
    Surface(
        color = SurfaceGrey,
        shape = RoundedCornerShape(24.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(18.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier.size(50.dp).background(client.color.copy(alpha = 0.1f), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    client.name.take(1),
                    color = client.color,
                    fontWeight = FontWeight.Black,
                    fontSize = 20.sp
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(client.name, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Text(client.project, color = Color.Gray, fontSize = 12.sp)
            }
            Column(horizontalAlignment = Alignment.End) {
                Text(client.value, color = Color.White, fontWeight = FontWeight.Black, fontSize = 14.sp)
                Text(client.status, color = SuccessGreen, fontSize = 11.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}

data class ClientData(val name: String, val project: String, val value: String, val status: String, val color: Color)

val clientList = listOf(
    ClientData("Acme Corp", "Enterprise ERP", "$4,500/mo", "Active", Color(0xFF5D9CEC)),
    ClientData("Global Tech", "Cloud Migration", "$12,000/mo", "Active", Color(0xFF4FC1E9)),
    ClientData("Stark Ind.", "AI Integration", "$25,000/mo", "Active", Color(0xFFAC92ED)),
    ClientData("Wayne Ent.", "Cybersecurity", "$15,000/mo", "Active", Color(0xFFEC87C0)),
    ClientData("Dunder Mifflin", "Supply Chain", "$2,200/mo", "Active", Color(0xFFFFCE54))
)
