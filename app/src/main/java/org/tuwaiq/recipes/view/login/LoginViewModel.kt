package org.tuwaiq.recipes.view.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.tuwaiq.recipes.repository.UserRepository

class LoginViewModel : ViewModel() {
    var userRepository = UserRepository()

    fun login(email: String, password: String): MutableLiveData<Boolean> {
        var mutableLiveData = MutableLiveData<Boolean>()

        userRepository.login(email, password).observeForever {
            if (it)
                mutableLiveData.postValue(true)
            else
                mutableLiveData.postValue(false)
        }

        return mutableLiveData
    }
}