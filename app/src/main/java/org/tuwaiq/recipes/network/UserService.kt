package org.tuwaiq.recipes.network

import org.tuwaiq.recipes.model.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface UserService {
    @POST("users")
    fun addUser(@Body user: User): Call<User>

    @GET("users")
    fun getUserByFBID(@Query("fbUid") uid: String): Call<List<User>>
}