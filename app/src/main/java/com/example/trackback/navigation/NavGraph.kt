package com.example.trackback.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.trackback.ui.screens.*

sealed class Screen(val route: String) {
    object Onboarding : Screen("onboarding")
    object Home : Screen("home/{domain}") {
        fun createRoute(domain: String) = "home/$domain"
    }
    object Chat : Screen("chat/{domain}") {
        fun createRoute(domain: String) = "chat/$domain"
    }
    object Analyzer : Screen("analyzer/{domain}") {
        fun createRoute(domain: String) = "analyzer/$domain"
    }
    object Tracker : Screen("tracker/{domain}") {
        fun createRoute(domain: String) = "tracker/$domain"
    }
    object Profile : Screen("profile/{domain}") {
        fun createRoute(domain: String) = "profile/$domain"
    }
    object Payouts : Screen("payouts/{domain}") {
        fun createRoute(domain: String) = "payouts/$domain"
    }
    object Clients : Screen("clients/{domain}") {
        fun createRoute(domain: String) = "clients/$domain"
    }
    object Vault : Screen("vault/{domain}") {
        fun createRoute(domain: String) = "vault/$domain"
    }
    object AuditLog : Screen("audit_log/{domain}") {
        fun createRoute(domain: String) = "audit_log/$domain"
    }
}

@Composable
fun GhostMentorNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Onboarding.route
    ) {
        composable(Screen.Onboarding.route) {
            OnboardingScreen(onNavigateToHome = { domain ->
                navController.navigate(Screen.Home.createRoute(domain)) {
                    popUpTo(Screen.Onboarding.route) { inclusive = true }
                }
            })
        }
        
        composable(
            route = Screen.Home.route,
            arguments = listOf(navArgument("domain") { type = NavType.StringType })
        ) { backStackEntry ->
            val domain = backStackEntry.arguments?.getString("domain") ?: "forex"
            HomeScreen(navController, domain)
        }
        
        composable(
            route = Screen.Chat.route,
            arguments = listOf(navArgument("domain") { type = NavType.StringType })
        ) { backStackEntry ->
            val domain = backStackEntry.arguments?.getString("domain") ?: "forex"
            ChatScreen(navController, domain)
        }
        
        composable(
            route = Screen.Analyzer.route,
            arguments = listOf(navArgument("domain") { type = NavType.StringType })
        ) { backStackEntry ->
            val domain = backStackEntry.arguments?.getString("domain") ?: "forex"
            AnalyzerScreen(navController, domain)
        }
        
        composable(
            route = Screen.Tracker.route,
            arguments = listOf(navArgument("domain") { type = NavType.StringType })
        ) { backStackEntry ->
            val domain = backStackEntry.arguments?.getString("domain") ?: "forex"
            TrackerScreen(navController, domain)
        }
        
        composable(
            route = Screen.Profile.route,
            arguments = listOf(navArgument("domain") { type = NavType.StringType })
        ) { backStackEntry ->
            val domain = backStackEntry.arguments?.getString("domain") ?: "forex"
            ProfileScreen(navController, domain)
        }

        composable(
            route = Screen.Payouts.route,
            arguments = listOf(navArgument("domain") { type = NavType.StringType })
        ) { backStackEntry ->
            val domain = backStackEntry.arguments?.getString("domain") ?: "forex"
            PayoutsScreen(navController, domain)
        }

        composable(
            route = Screen.Clients.route,
            arguments = listOf(navArgument("domain") { type = NavType.StringType })
        ) { backStackEntry ->
            val domain = backStackEntry.arguments?.getString("domain") ?: "forex"
            ClientsScreen(navController, domain)
        }

        composable(
            route = Screen.Vault.route,
            arguments = listOf(navArgument("domain") { type = NavType.StringType })
        ) { backStackEntry ->
            val domain = backStackEntry.arguments?.getString("domain") ?: "forex"
            VaultScreen(navController, domain)
        }

        composable(
            route = Screen.AuditLog.route,
            arguments = listOf(navArgument("domain") { type = NavType.StringType })
        ) { backStackEntry ->
            val domain = backStackEntry.arguments?.getString("domain") ?: "forex"
            AuditLogScreen(navController, domain)
        }
    }
}
