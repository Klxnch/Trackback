package com.example.trackback.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.RemoveRedEye
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
fun VaultScreen(navController: NavHostController, domain: String) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Secured Vault", fontWeight = FontWeight.Bold) },
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
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(top = 20.dp)
        ) {
            item {
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(24.dp),
                    color = Color(0xFFAC92ED).copy(alpha = 0.1f),
                    border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFAC92ED).copy(alpha = 0.3f))
                ) {
                    Row(
                        modifier = Modifier.padding(24.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(Icons.Default.Lock, contentDescription = null, tint = Color(0xFFAC92ED), modifier = Modifier.size(40.dp))
                        Spacer(modifier = Modifier.width(20.dp))
                        Column {
                            Text("Vault Status", color = Color.Gray, fontSize = 13.sp)
                            Text("Encrypted & Locked", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Black)
                        }
                    }
                }
            }

            item {
                Text("Stored Credentials", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
            }

            items(vaultItems) { item ->
                VaultItemRow(item)
            }
        }
    }
}

@Composable
fun VaultItemRow(item: VaultData) {
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
            Column {
                Text(item.title, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 15.sp)
                Text(item.lastAccessed, color = Color.Gray, fontSize = 12.sp)
            }
            IconButton(onClick = { }) {
                Icon(Icons.Default.RemoveRedEye, contentDescription = "View", tint = Color.Gray)
            }
        }
    }
}

data class VaultData(val title: String, val lastAccessed: String)

val vaultItems = listOf(
    VaultData("Stripe API Keys", "Accessed 2h ago"),
    VaultData("AWS Production Credentials", "Accessed 1d ago"),
    VaultData("Corporate Bank Access", "Accessed 5d ago"),
    VaultData("Encryption Master Key", "Accessed 2w ago")
)
