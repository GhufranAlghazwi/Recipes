package org.tuwaiq.recipes.view.home.recipes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import okhttp3.*
import org.tuwaiq.recipes.model.Recipe
import org.tuwaiq.recipes.repository.RecipesRepository
import java.io.IOException

class RecipesViewModel: ViewModel() {
    var recipeRepository = RecipesRepository()

    fun getVegRecipes():MutableLiveData<List<Recipe>>{
        var mLiveData = MutableLiveData<List<Recipe>>()
        val client = OkHttpClient()
        val request = Request.Builder()
            .url("https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com/recipes/random?tags=vegetarian&number=20")
            .get()
            .addHeader("x-rapidapi-host", "spoonacular-recipe-food-nutrition-v1.p.rapidapi.com")
            .addHeader("x-rapidapi-key", "156c19c825mshf64b5476dd8c6a6p1542f1jsnc85957b48ca8")
            .build()

        val response = client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                TODO("Not yet implemented")
            }

            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful){
//                    val mResponse = response.body().toString()
                    val body = response?.body?.toString()
                    println(body)
                }
            }
        })
//        val list = response.body()

        return mLiveData
    }


    fun getRecipes(): LiveData<List<Recipe>> {
        return recipeRepository.getVegRecipes2()
    }





}