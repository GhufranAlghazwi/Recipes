package org.tuwaiq.recipes.view.editRecipe

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.tuwaiq.recipes.model.Recipe
import org.tuwaiq.recipes.repository.RecipesRepository
import org.tuwaiq.recipes.view.home.recipes.RecipesViewModel

class EditRecipeViewModel: ViewModel() {

    fun updateRecipe(id: String, recipe: Recipe): MutableLiveData<Recipe>{
        var mLiveData = MutableLiveData<Recipe>()
        RecipesRepository().updateRecipe(id, recipe).observeForever {
            if (it != null)
                mLiveData.postValue(it)
            else
                mLiveData.postValue(Recipe("","","","","","","",""))
        }
        return mLiveData
    }
}