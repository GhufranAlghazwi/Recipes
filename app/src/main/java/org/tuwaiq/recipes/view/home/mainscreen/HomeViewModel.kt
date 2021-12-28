package org.tuwaiq.recipes.view.home.mainscreen

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
            } else {
                mLiveData.postValue(false)
            }
        }

        return mLiveData
    }

}