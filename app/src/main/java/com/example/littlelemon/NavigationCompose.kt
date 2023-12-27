package com.example.littlelemon

import android.content.Context
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigation(menuItems: List<MenuItemRoom>) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = determineStartDestination(getDataFromSharedPreferences())
    ) {
        composable(Onboarding.route) {
            Onboarding(navController = navController)
        }
        composable(Profile.route) {
            Profile(navController = navController)
        }
        composable(Home.route) {
            Home(navController = navController, menuItems)
        }
    }
}

@Composable
fun determineStartDestination(data: Triple<String, String, String>): String {
    val (firstName, lastName, email) = data
    return if (firstName.isNotBlank() && lastName.isNotBlank() && email.isNotBlank()) {
        Home.route
    } else {
        Onboarding.route
    }
}

@Composable
fun getDataFromSharedPreferences(): Triple<String, String, String> {
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("LITTLE_LEMON", Context.MODE_PRIVATE)
    val firstName = sharedPreferences.getString("FIRST_NAME", "") ?: ""
    val lastName = sharedPreferences.getString("LAST_NAME", "") ?: ""
    val email = sharedPreferences.getString("EMAIL", "") ?: ""

    return Triple(firstName, lastName, email)
}
