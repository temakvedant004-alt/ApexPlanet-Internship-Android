package com.example.internshipbasicapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

// Sealed class to represent the different states of the data fetching process
sealed class RecipeUiState {
    data object Loading : RecipeUiState()
    data class Success(val recipes: List<Recipe>) : RecipeUiState()
    data class Error(val message: String) : RecipeUiState()
}

class RecipeViewModel : ViewModel() {
    private val _uiState = MutableStateFlow<RecipeUiState>(RecipeUiState.Loading)
    val uiState: StateFlow<RecipeUiState> = _uiState

    // API Key from Spoonacular - REPLACE WITH YOUR ACTUAL KEY!
    private val API_KEY = "34c1f0af7e1c40feae42a9e639f03caf"

    init {
        // Fetch recipes immediately when the ViewModel is created (for testing)
        fetchRecipes("pasta") // Use a default test query
    }

    fun fetchRecipes(query: String) {
        viewModelScope.launch {
            _uiState.value = RecipeUiState.Loading
            try {
                // Call the API service
                val response = RetrofitClient.recipeApiService.searchRecipes(query, API_KEY)
                // If successful, update the state with the list of recipes
                _uiState.value = RecipeUiState.Success(response.results)
            } catch (e: Exception) {
                // If network or API error occurs, update the state
                _uiState.value = RecipeUiState.Error("Failed to fetch recipes: ${e.message}")
            }
        }
    }
}