package org.tuwaiq.recipes.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SpoonacularAPI {

    companion object{
        private  val retrofit: Retrofit
        init {
            retrofit= Retrofit.Builder()
                .baseUrl("https://api.spoonacular.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        fun getInstance(): Retrofit {
            return retrofit
        }
    }
}