package org.tuwaiq.recipes.repository

import androidx.lifecycle.MutableLiveData
import org.tuwaiq.recipes.model.Recipe
import org.tuwaiq.recipes.model.User
import org.tuwaiq.recipes.network.API
import org.tuwaiq.recipes.network.RecipeService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RecipesRepository {
    val recipeService= API.getInstance().create(RecipeService::class.java)

    fun getDrinks(): MutableLiveData<List<Recipe>>{

        var mutableLiveData=MutableLiveData<List<Recipe>>()

        recipeService.getDrinks().enqueue(object : Callback<List<Recipe>> {
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

    fun getDesserts(): MutableLiveData<List<Recipe>>{
        var mutableLiveData=MutableLiveData<List<Recipe>>()

        recipeService.getDessert().enqueue(object : Callback<List<Recipe>> {
            override fun onResponse(call: Call<List<Recipe>>, response: Response<List<Recipe>>) {
                mutableLiveData.postValue(response.body())
            }

            override fun onFailure(call: Call<List<Recipe>>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
        return mutableLiveData
    }

    fun getRecipeByUserID(uid: String):MutableLiveData<List<Recipe>>{
        var mLiveData = MutableLiveData<List<Recipe>>()

        recipeService.getUserRecipes(uid).enqueue(object : Callback<List<Recipe>> {
            override fun onResponse(call: Call<List<Recipe>>, response: Response<List<Recipe>>) {
                mLiveData.postValue(response.body())
            }

            override fun onFailure(call: Call<List<Recipe>>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })

        return mLiveData
    }

    fun getAllRecipes(): MutableLiveData<List<Recipe>>{
        var mutableLiveData=MutableLiveData<List<Recipe>>()

        recipeService.getAllRecipes().enqueue(object : Callback<List<Recipe>> {
            override fun onResponse(call: Call<List<Recipe>>, response: Response<List<Recipe>>) {
                mutableLiveData.postValue(response.body())
            }

            override fun onFailure(call: Call<List<Recipe>>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
        return mutableLiveData
    }

    fun search(key: String): MutableLiveData<List<Recipe>>{
        var mutableLiveData=MutableLiveData<List<Recipe>>()

        recipeService.search(key).enqueue(object : Callback<List<Recipe>> {
            override fun onResponse(call: Call<List<Recipe>>, response: Response<List<Recipe>>) {
                mutableLiveData.postValue(response.body())
            }

            override fun onFailure(call: Call<List<Recipe>>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
        return mutableLiveData
    }

    fun addRecipe(recipe: Recipe): MutableLiveData<Recipe>{
        var mLiveData = MutableLiveData<Recipe>()
        recipeService.addRecipe(recipe)
            .enqueue(object : Callback<Recipe> {
                override fun onResponse(call: Call<Recipe>, response: Response<Recipe>) {
                    if(response.isSuccessful)
                        mLiveData.postValue(response.body())
                    else
                        mLiveData.postValue(Recipe("","","","","","","",""))
                }
                override fun onFailure(call: Call<Recipe>, t: Throwable) {
                    TODO("Not yet implemented")
                }
            })
        return mLiveData
    }
}