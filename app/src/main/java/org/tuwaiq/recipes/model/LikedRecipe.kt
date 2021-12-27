package org.tuwaiq.recipes.model

import java.io.Serializable

data class LikedRecipe(
    val category: String,
    val id: String,
    val image: String,
    val ingredients: String,
    val instructions: String,
    val likeId: String,
    val readyInMinutes: String,
    val title: String,
    val uid: String
): Serializable