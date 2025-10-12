package com.example.internshipbasicapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.internshipbasicapp.ui.theme.InternshipBasicAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            InternshipBasicAppTheme {
                AppNavigation()
            }
        }
    }
}

// This function holds the Navigation logic and the two screen definitions
@Composable
fun AppNavigation() {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        val navController = rememberNavController()

        // Define the NavHost with the start screen and available destinations
        NavHost(
            navController = navController,
            startDestination = "search_screen"
        ) {
            // 1. The Recipe Search Screen (Start Screen)
            composable("search_screen") {
                RecipeSearchScreen(
                    onNavigateToDetail = { navController.navigate("detail_screen") }
                )
            }

            // 2. The Recipe Detail Screen
            composable("detail_screen") {
                RecipeDetailScreen()
            }
        }
    }
}

// RECIPE SEARCH SCREEN COMPOSABLE (Definition for the Start Screen)
@Composable
fun RecipeSearchScreen(onNavigateToDetail: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(text = "FlavorFind - Search Screen", fontSize = 32.sp)
        Text(text = "Search Bar Placeholder")
        Text(text = "Recipe List/Cards will be built here")

        // Button to test navigation for submission proof
        Button(onClick = onNavigateToDetail) {
            Text("Go to Recipe Detail")
        }
    }
}

// RECIPE DETAIL SCREEN COMPOSABLE (Definition for the Second Screen)
@Composable
fun RecipeDetailScreen() {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(text = "Recipe Detail Screen", fontSize = 32.sp)
        Text(text = "Large Recipe Image Placeholder")
        Text(text = "Classic Chicken Curry (Title)")
        Text(text = "Ingredients: 1 cup chicken, 1 onion...")
    }
}