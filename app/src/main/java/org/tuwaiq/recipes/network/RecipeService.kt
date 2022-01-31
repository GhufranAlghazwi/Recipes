package org.tuwaiq.recipes.network

import org.tuwaiq.recipes.model.Recipe
import retrofit2.Call
import retrofit2.http.*

interface RecipeService {

    @GET("recipes/?category=drink&order=desc&sortBy=id")
    fun getDrinks(): Call<List<Recipe>>

    @GET("recipes/?category=main dish&order=desc&sortBy=id")
    fun getMainDishes(): Call<List<Recipe>>

    @GET("recipes/?category=dessert&order=desc&sortBy=id")
    fun getDessert(): Call<List<Recipe>>

    @GET("recipes?order=desc&sortBy=id")
    fun getUserRecipes(@Query("uid") uid: String): Call<List<Recipe>>

    @GET("recipes?order=desc&sortBy=id")
    fun getAllRecipes(): Call<List<Recipe>>

    @GET("recipes")
    fun search(@Query("title") key: String): Call<List<Recipe>>

    @POST("recipes")
    fun addRecipe(@Body recipe: Recipe): Call<Recipe>

    @DELETE("recipes/{id}")
    fun deleteRecipe(@Path("id")id: String):Call<Recipe>

    @PUT("recipes/{id}")
    fun updateRecipe(@Path("id")id: String, @Body recipe: Recipe): Call<Recipe>
}