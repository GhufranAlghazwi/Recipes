package org.tuwaiq.recipes.view.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.tuwaiq.recipes.model.User
import org.tuwaiq.recipes.repository.UserRepository

class LoginViewModel : ViewModel() {
    var userRepository = UserRepository()

    fun login(email: String, password: String): MutableLiveData<String> {
        var mutableLiveData = MutableLiveData<String>()

        userRepository.login(email, password).observeForever {
            if (it != null)
                mutableLiveData.postValue(it)
            else
                mutableLiveData.postValue("")
        }
        return mutableLiveData
    }

    fun getUserByFBID(FB_ID: String): MutableLiveData<List<User>> {
        return userRepository.getUserByFbID(FB_ID)
    }
}