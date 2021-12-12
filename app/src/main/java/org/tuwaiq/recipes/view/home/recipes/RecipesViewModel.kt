package org.tuwaiq.recipes.view.home.recipes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import okhttp3.*
import org.tuwaiq.recipes.model.Recipe
import org.tuwaiq.recipes.repository.RecipesRepository
import java.io.IOException

class RecipesViewModel: ViewModel() {
    var recipesRepository = RecipesRepository()

    fun getVege(): LiveData<List<Recipe>> {
        return recipesRepository.getVegeRecipes()
    }

    fun getMain(): LiveData<List<Recipe>> {
        return recipesRepository.getMainDishes()
    }

}