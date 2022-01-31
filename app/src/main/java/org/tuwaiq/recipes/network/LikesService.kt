package org.tuwaiq.recipes.network

import org.tuwaiq.recipes.model.LikedRecipe
import org.tuwaiq.recipes.model.Likes
import org.tuwaiq.recipes.model.Recipe
import org.tuwaiq.recipes.model.User
import retrofit2.Call
import retrofit2.http.*

interface LikesService {
    @GET("users/{id}/likes")
    fun getUserLidByUID(@Path("id") uid: String, @Query("userId") userId: String): Call<List<Likes>>

    @POST("users/{id}/likes")
    fun createNewLikesList(@Path("id") id: String, @Body likes: Likes): Call<Likes>

    @POST("users/{id}/likes/{lid}/recipe")
    fun addLikedRecipe(@Path("id") uid: String, @Path("lid") lid: String, @Body likedRecipe: LikedRecipe): Call<LikedRecipe>

    @GET("users/{id}/likes/{lid}/recipe?order=desc&sortBy=id")
    fun getUserLikes(@Path("id") uid: String, @Path("lid") lid: String): Call<List<Recipe>>

    @GET("users/{id}/likes/{lid}/recipe")
    fun getLikedRecipeByName(@Path("id") uid: String, @Path("lid") lid: String, @Query("title") rid: String): Call<List<LikedRecipe>>

    @DELETE("users/{id}/likes/{lid}/recipe/{rid}")
    fun removeFromLikes(@Path("id") uid: String, @Path("lid") lid: String, @Path("rid") rid: String): Call<LikedRecipe>


}