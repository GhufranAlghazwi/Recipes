package org.tuwaiq.recipes.view.home.addRecipe

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.tuwaiq.recipes.model.Recipe
import org.tuwaiq.recipes.repository.RecipesRepository

class AddRecipeViewModel: ViewModel() {

    fun addRecipe(recipe: Recipe): MutableLiveData<Recipe>{
        var mLiveData = MutableLiveData<Recipe>()

        RecipesRepository().addRecipe(recipe)
            .observeForever{
                if (it != null)
                    mLiveData.postValue(it)
                else
                    mLiveData.postValue(Recipe("","","","","","","",""))
            }

        return mLiveData
    }
}