package org.tuwaiq.recipes.network

import org.tuwaiq.recipes.model.LikedRecipe
import org.tuwaiq.recipes.model.Likes
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
}