package org.tuwaiq.recipes.view.home.profile.likes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.tuwaiq.recipes.model.LikedRecipe
import org.tuwaiq.recipes.model.Likes
import org.tuwaiq.recipes.repository.LikesRepository

class LikesViewModel: ViewModel() {

    fun getLidByUid(uid: String, userId: String): MutableLiveData<List<Likes>>{
        return LikesRepository().getLidByUid(uid, userId)
    }

    fun createLikesList(id: String, likes: Likes): LiveData<Likes>{
        var mLiveData = MutableLiveData<Likes>()
        LikesRepository().createLikesList(id, likes).observeForever {
            if (it != null)
                mLiveData.postValue(it)
            else
                mLiveData.postValue(Likes("",""))
        }

        return mLiveData
    }

    fun addToLikes(uid: String, lid: String, likedRecipe: LikedRecipe): LiveData<Boolean>{
        var mLiveData = MutableLiveData<Boolean>()

        LikesRepository().addToLikes(uid, lid, likedRecipe).observeForever {
            if (it != null)
                mLiveData.postValue(true)
            else
                mLiveData.postValue(false)
        }

        return mLiveData
    }
}