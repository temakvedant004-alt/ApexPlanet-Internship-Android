package com.example.internshipbasicapp

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// Base URL for the Spoonacular API
private const val BASE_URL = "https://api.spoonacular.com/"

// Singleton object to ensure only one instance of Retrofit is created
object RetrofitClient {

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        // Add the converter factory to automatically parse JSON into Kotlin data classes
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    // Public property to expose the RecipeApiService instance
    val recipeApiService: RecipeApiService by lazy {
        retrofit.create(RecipeApiService::class.java)
    }
}