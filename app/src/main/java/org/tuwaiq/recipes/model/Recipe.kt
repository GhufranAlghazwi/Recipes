package org.tuwaiq.recipes.model

data class Recipe(
    var title: String,
    var image: String,
    var readyInMinutes: String,
    var instructions: String,
    var category: String,
    var ingredients: String,
    var uid:String,
    var id: String
) {
}