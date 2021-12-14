package org.tuwaiq.recipes.view.home.recipes

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import org.tuwaiq.recipes.model.Ingredient
import org.tuwaiq.recipes.model.Recipe
import org.tuwaiq.recipes.repository.RecipesRepository

class RecipesViewModel: ViewModel() {
    var recipesRepository = RecipesRepository()

    fun getVege(): LiveData<List<Recipe>> {
        return recipesRepository.getVegeRecipes()
    }

    fun getMain(): LiveData<List<Recipe>> {
        return recipesRepository.getMainDishes()
    }

    fun getDessert(): LiveData<List<Recipe>> {
        return recipesRepository.getDesserts()
    }

    fun getAll(): LiveData<List<Recipe>>{
        return recipesRepository.getAllRecipes()
    }



}