package org.tuwaiq.recipes.view.home.addRecipe

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.tuwaiq.recipes.repository.RecipesRepository

class AddRecipeViewModel: ViewModel() {

    fun addRecipe(title: String, image: String, time: String, instructions: String, category: String,
                  ingr: String, uid: String): MutableLiveData<Boolean>{
        var mLiveData = MutableLiveData<Boolean>()

        RecipesRepository().addRecipe(title, image, time, instructions, category, ingr, uid)
            .observeForever{
                if (it != null)
                    mLiveData.postValue(true)
                else
                    mLiveData.postValue(false)
            }

        return mLiveData
    }
}