package org.tuwaiq.recipes.view.home.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import org.tuwaiq.recipes.model.Recipe
import org.tuwaiq.recipes.repository.RecipesRepository

class SearchViewModel: ViewModel() {

    fun search(key: String): LiveData<List<Recipe>>{
        return RecipesRepository().search(key)
    }
}