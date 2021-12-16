package org.tuwaiq.recipes.view.home.addRecipe

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.tuwaiq.recipes.model.Recipe
import org.tuwaiq.recipes.repository.RecipesRepository

class AddRecipeViewModel: ViewModel() {

    fun addRecipe(recipe: Recipe): MutableLiveData<Boolean>{
        var mLiveData = MutableLiveData<Boolean>()

        RecipesRepository().addRecipe(recipe)
            .observeForever{
                if (it != null)
                    mLiveData.postValue(true)
                else
                    mLiveData.postValue(false)
            }

        return mLiveData
    }
}