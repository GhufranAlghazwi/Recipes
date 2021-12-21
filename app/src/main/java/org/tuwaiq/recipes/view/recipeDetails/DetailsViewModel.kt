package org.tuwaiq.recipes.view.recipeDetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.tuwaiq.recipes.repository.RecipesRepository

class DetailsViewModel: ViewModel() {

    fun deleteRecipe(id: String): MutableLiveData<Boolean>{
        return RecipesRepository().deleteRecipe(id)
    }
}