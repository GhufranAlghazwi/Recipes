package org.tuwaiq.recipes.view.register

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.tuwaiq.recipes.repository.UserRepository

class RegisterViewModel: ViewModel() {
    var userRepository = UserRepository()

    fun register(name: String, email: String, password: String): MutableLiveData<Boolean>{
        var mLiveData = MutableLiveData<Boolean>()

        userRepository.register(name,email,password).observeForever{
            if (it)
                mLiveData.postValue(true)
            else
                mLiveData.postValue(false)
        }

        return mLiveData
    }
}