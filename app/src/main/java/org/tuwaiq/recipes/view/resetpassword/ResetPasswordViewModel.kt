package org.tuwaiq.recipes.view.resetpassword

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.tuwaiq.recipes.repository.UserRepository

class ResetPasswordViewModel: ViewModel() {

    var repository = UserRepository()
    fun resetPass(email: String): MutableLiveData<Boolean>{
        var mLiveData = MutableLiveData<Boolean>()

        repository.resetPassword(email).observeForever{
            if (it)
                mLiveData.postValue(true)
            else
                mLiveData.postValue(false)
        }

        return mLiveData
    }
}