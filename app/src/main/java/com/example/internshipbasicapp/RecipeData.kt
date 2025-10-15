package com.example.internshipbasicapp

// Simple data class for a recipe item in the search results
data class Recipe(
    val id: Int,
    val title: String,
    val image: String
)

// The overall structure of the search results response
data class RecipeSearchResponse(
    val results: List<Recipe>,
    val offset: Int,
    val number: Int,
    val totalResults: Int
)