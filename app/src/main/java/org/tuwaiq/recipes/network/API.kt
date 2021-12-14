package org.tuwaiq.recipes.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class API {

    companion object{
        private  val retrofit: Retrofit
        init {
            retrofit= Retrofit.Builder()
                .baseUrl("https://61b8cf2f38f69a0017ce5d7f.mockapi.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        fun getInstance(): Retrofit {
            return retrofit
        }
    }

}