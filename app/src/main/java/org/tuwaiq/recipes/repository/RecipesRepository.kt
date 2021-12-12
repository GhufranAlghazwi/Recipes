package org.tuwaiq.recipes.repository

import androidx.lifecycle.MutableLiveData
import okhttp3.OkHttpClient
import okhttp3.Request
import org.tuwaiq.recipes.model.Recipe
import org.tuwaiq.recipes.network.API
import org.tuwaiq.recipes.network.RecipeService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RecipesRepository {
    val recipeService= API.getInstance().create(RecipeService::class.java)

    fun getVegeRecipes(): MutableLiveData<List<Recipe>>{

        var mutableLiveData=MutableLiveData<List<Recipe>>()

        recipeService.getVegRecipes().enqueue(object : Callback<List<Recipe>> {
            override fun onResponse(call: Call<List<Recipe>>, response: Response<List<Recipe>>) {
                mutableLiveData.postValue(response.body())
            }

            override fun onFailure(call: Call<List<Recipe>>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })

        return mutableLiveData
    }

    fun getMainDishes(): MutableLiveData<List<Recipe>>{

        var mutableLiveData=MutableLiveData<List<Recipe>>()

        recipeService.getMainDishes().enqueue(object : Callback<List<Recipe>> {
            override fun onResponse(call: Call<List<Recipe>>, response: Response<List<Recipe>>) {
                mutableLiveData.postValue(response.body())
            }

            override fun onFailure(call: Call<List<Recipe>>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })

        return mutableLiveData
    }
}