package org.tuwaiq.recipes.network

import org.tuwaiq.recipes.model.Recipe
import retrofit2.Call
import retrofit2.http.GET

interface RecipeService {

    @GET("recipes/?category=vegetarian")
    fun getVegRecipes(): Call<List<Recipe>>

    @GET("recipes/?category=main dish")
    fun getMainDishes(): Call<List<Recipe>>

    @GET("recipes/?category=dessert")
    fun getDessert(): Call<List<Recipe>>

//    @GET("recipes/random?tags=vegetarian&number=20")
//    fun getVegRecipes2(): Call<List<Recipe>>
}