package com.example.internshipbasicapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
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

@Composable
fun AppNavigation() {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        val navController = rememberNavController()

        NavHost(
            navController = navController,
            startDestination = "search_screen"
        ) {
            composable("search_screen") {
                // Initialize the ViewModel here and pass it to the screen
                val viewModel: RecipeViewModel = viewModel()
                RecipeSearchScreen(
                    onNavigateToDetail = { navController.navigate("detail_screen") },
                    viewModel = viewModel // Pass the ViewModel instance
                )
            }

            composable("detail_screen") {
                RecipeDetailScreen()
            }
        }
    }
}

// RECIPE SEARCH SCREEN COMPOSABLE (NOW WITH VIEWMODEL AND API LOGIC)
@Composable
fun RecipeSearchScreen(
    onNavigateToDetail: () -> Unit,
    viewModel: RecipeViewModel // ViewModel is passed from NavHost scope
) {
    // Collect the current state from the ViewModel
    val state by viewModel.uiState.collectAsState()

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(text = "FlavorFind - Search Screen", fontSize = 32.sp)
        Spacer(modifier = Modifier.height(16.dp)) // Add space after title

        // 1. Loading Indicator / Error Message / Success List
        when (state) {
            is RecipeUiState.Loading -> {
                Text("Loading Recipes from API...")
                CircularProgressIndicator(modifier = Modifier.padding(top = 8.dp))
            }
            is RecipeUiState.Error -> {
                Text(
                    "Error: ${(state as RecipeUiState.Error).message}",
                    color = androidx.compose.ui.graphics.Color.Red
                )
            }
            is RecipeUiState.Success -> {
                // Display the actual recipe list
                val recipes = (state as RecipeUiState.Success).recipes

                if (recipes.isEmpty()) {
                    Text("No recipes found for the search query.")
                } else {
                    Text("Found ${recipes.size} Recipes:", fontSize = 16.sp)
                    Spacer(modifier = Modifier.height(8.dp))

                    LazyColumn {
                        items(recipes) { recipe ->
                            // Display the fetched recipe title (Proof of API integration!)
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable { onNavigateToDetail() } // Go to detail on click
                                    .padding(vertical = 8.dp)
                            ) {
                                Text(text = recipe.title, fontSize = 18.sp)
                                Text(text = "ID: ${recipe.id}", fontSize = 12.sp, color = androidx.compose.ui.graphics.Color.Gray)
                            }
                            Divider() // Separator
                        }
                    }
                }
            }
        }

        // The temporary navigation button for submission proof (Optional, but useful)
        Button(
            onClick = onNavigateToDetail,
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("Go to Recipe Detail (Test)")
        }
    }
}

// RECIPE DETAIL SCREEN COMPOSABLE (Placeholder for Task 2 structure)
@Composable
fun RecipeDetailScreen() {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(text = "Recipe Detail Screen", fontSize = 32.sp)
        Text(text = "Large Recipe Image Placeholder")
        Text(text = "Classic Chicken Curry (Title)")
        Text(text = "Ingredients: 1 cup chicken, 1 onion...")
    }
}