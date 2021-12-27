package org.tuwaiq.recipes.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import org.tuwaiq.recipes.model.LikedRecipe
import org.tuwaiq.recipes.model.Likes
import org.tuwaiq.recipes.network.API
import org.tuwaiq.recipes.network.LikesService
import org.tuwaiq.recipes.network.RecipeService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LikesRepository {
    val likesService = API.getInstance().create(LikesService::class.java)

    fun getLidByUid(uid: String, userId: String): MutableLiveData<List<Likes>>{
        var mLiveData = MutableLiveData<List<Likes>>()

        likesService.getUserLidByUID(uid,userId).enqueue(object : Callback<List<Likes>> {
            override fun onResponse(call: Call<List<Likes>>, response: Response<List<Likes>>) {
                mLiveData.postValue(response.body())
            }

            override fun onFailure(call: Call<List<Likes>>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
        return mLiveData
    }

    fun createLikesList(id: String, likes: Likes): LiveData<Likes> {
        var mLiveData = MutableLiveData<Likes>()

        likesService.createNewLikesList(id, likes).enqueue(object : Callback<Likes> {
            override fun onResponse(call: Call<Likes>, response: Response<Likes>) {
                if (response.isSuccessful)
                    mLiveData.postValue(response.body())
                else
                    mLiveData.postValue(Likes("",""))
            }

            override fun onFailure(call: Call<Likes>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })

        return mLiveData
    }

    fun addToLikes(uid: String, lid: String, likedRecipe: LikedRecipe): LiveData<LikedRecipe>{
        var mLiveData = MutableLiveData<LikedRecipe>()

        likesService.addLikedRecipe(uid,lid, likedRecipe).enqueue(object : Callback<LikedRecipe> {
            override fun onResponse(call: Call<LikedRecipe>, response: Response<LikedRecipe>) {
                if (response.isSuccessful){
                    mLiveData.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<LikedRecipe>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
        return mLiveData
    }
}