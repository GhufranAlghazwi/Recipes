package org.tuwaiq.recipes.view.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseUser
import org.tuwaiq.recipes.repository.UserRepository

class HomeViewModel : ViewModel() {

    var userRepository = UserRepository()

    fun checkLogin(user: FirebaseUser?): MutableLiveData<Boolean> {
        var mLiveData = MutableLiveData<Boolean>()

        userRepository.checkLogin(user).observeForever {
            if (it) {
                mLiveData.postValue(true)
                println("There is logged in user HVM")
            } else {
                mLiveData.postValue(false)
                println("No user logged in HVM")
            }
        }

        return mLiveData
    }

}