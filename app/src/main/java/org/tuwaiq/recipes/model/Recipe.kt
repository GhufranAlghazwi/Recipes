package org.tuwaiq.recipes.model

import java.io.Serializable

data class Recipe(
    var title: String,
    var image: String,
    var readyInMinutes: String,
    var instructions: String,
    var category: String,
    var ingredients: String,
    var uid:String,
    var id: String
): Serializable