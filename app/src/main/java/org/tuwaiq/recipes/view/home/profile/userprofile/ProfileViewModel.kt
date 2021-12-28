package org.tuwaiq.recipes.view.home.profile.userprofile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.tuwaiq.recipes.model.Recipe
import org.tuwaiq.recipes.repository.RecipesRepository

class ProfileViewModel: ViewModel() {

    fun getUserRecipes(uid: String): MutableLiveData<List<Recipe>>{
        return RecipesRepository().getRecipeByUserID(uid)
    }
}