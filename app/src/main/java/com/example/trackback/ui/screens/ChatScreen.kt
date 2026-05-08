package com.example.trackback.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.trackback.ui.theme.*

data class Message(val text: String, val isFromUser: Boolean)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen(navController: NavHostController, domain: String) {
    var messageText by remember { mutableStateOf("") }
    var harshTruthMode by remember { mutableStateOf(false) }
    
    val mentorName = when(domain) {
        "business" -> "Venture Ghost"
        "fitness" -> "Iron Ghost"
        else -> "Ghost Mentor"
    }

    val initialMessage = when(domain) {
        "business" -> "I am your Venture Ghost. How is the scale going? Are we chasing growth or vanity metrics?"
        "fitness" -> "I am your Iron Ghost. Did you push your limits today or just go through the motions?"
        else -> "I am your Ghost Mentor. What's on your mind? Did the market humble you today?"
    }

    val messages = remember { mutableStateListOf(
        Message(initialMessage, false)
    ) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { 
                    Text(
                        mentorName, 
                        fontWeight = FontWeight.Bold,
                        style = TextStyle(
                            brush = Brush.horizontalGradient(listOf(GhostPurple, NeonBlue))
                        )
                    ) 
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.White)
                    }
                },
                actions = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(end = 8.dp)
                    ) {
                        Text(
                            "Harsh Truth", 
                            fontSize = 11.sp, 
                            fontWeight = FontWeight.Bold,
                            color = if (harshTruthMode) ErrorRed else Color.Gray
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Switch(
                            checked = harshTruthMode,
                            onCheckedChange = { harshTruthMode = it },
                            colors = SwitchDefaults.colors(
                                checkedThumbColor = Color.White,
                                checkedTrackColor = ErrorRed,
                                uncheckedThumbColor = Color.Gray,
                                uncheckedTrackColor = SurfaceGrey
                            ),
                            modifier = Modifier.scale(0.8f)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = DarkGrey)
            )
        },
        containerColor = DarkGrey
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 16.dp),
                contentPadding = PaddingValues(vertical = 16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(messages) { message ->
                    ChatBubble(message)
                }
            }

            Surface(
                color = SurfaceGrey,
                tonalElevation = 8.dp,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextField(
                        value = messageText,
                        onValueChange = { messageText = it },
                        modifier = Modifier.weight(1f),
                        placeholder = { Text("Ask your mentor...", color = Color.Gray) },
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = DarkGrey,
                            unfocusedContainerColor = DarkGrey,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            cursorColor = NeonBlue,
                            focusedTextColor = Color.White
                        ),
                        shape = RoundedCornerShape(24.dp)
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    FloatingActionButton(
                        onClick = {
                            if (messageText.isNotBlank()) {
                                messages.add(Message(messageText, true))
                                
                                val response = when(domain) {
                                    "business" -> if (harshTruthMode) {
                                        "Your burn rate is unsustainable. You're playing at business, not building one. Fix your unit economics or face the void."
                                    } else {
                                        "The market feedback suggests your value proposition needs sharpening. Pivot slightly or increase your outreach to find product-market fit."
                                    }
                                    "fitness" -> if (harshTruthMode) {
                                        "You're making excuses. One missed session becomes a habit of failure. Get back in the gym and stop negotiating with your weakness."
                                    } else {
                                        "Your progress is steady, but your recovery might be the bottleneck. Focus on sleep and micronutrients this week for maximum hypertrophy."
                                    }
                                    else -> if (harshTruthMode) {
                                        "That was a reckless move. Your risk management is non-existent. Go back to the basics before you blow your account."
                                    } else {
                                        "I see what you did there. However, the market structure didn't support that entry. Consider waiting for a clearer CRT setup next time."
                                    }
                                }
                                messages.add(Message(response, false))
                                messageText = ""
                            }
                        },
                        containerColor = GhostPurple,
                        contentColor = Color.White,
                        shape = CircleShape,
                        modifier = Modifier.size(48.dp)
                    ) {
                        Icon(Icons.Default.Send, contentDescription = "Send", modifier = Modifier.size(20.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun ChatBubble(message: Message) {
    val alignment = if (message.isFromUser) Alignment.End else Alignment.Start
    val bubbleColor = if (message.isFromUser) GhostPurple else SurfaceGrey
    val textColor = Color.White

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = alignment
    ) {
        Surface(
            color = bubbleColor,
            shape = RoundedCornerShape(
                topStart = 20.dp,
                topEnd = 20.dp,
                bottomStart = if (message.isFromUser) 20.dp else 4.dp,
                bottomEnd = if (message.isFromUser) 4.dp else 20.dp
            ),
            shadowElevation = if (message.isFromUser) 4.dp else 0.dp
        ) {
            Text(
                text = message.text,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp),
                color = textColor,
                fontSize = 15.sp,
                lineHeight = 20.sp
            )
        }
    }
}
