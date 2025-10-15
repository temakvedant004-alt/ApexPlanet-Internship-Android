package com.example.internshipbasicapp

import retrofit2.http.GET
import retrofit2.http.Query

// Define the API endpoint structure
interface RecipeApiService {

    // Function to search for recipes
    @GET("recipes/complexSearch")
    suspend fun searchRecipes(
        // The search query (e.g., "pasta")
        @Query("query") query: String,

        // This is your API key. REPLACE WITH YOUR REAL KEY!
        @Query("apiKey") apiKey: String = "34c1f0af7e1c40feae42a9e639f03caf",

        // Return max 10 results for simplicity
        @Query("number") number: Int = 10,

        // Include the image type in the result
        @Query("includeNutrition") includeNutrition: Boolean = true
    ): RecipeSearchResponse
}