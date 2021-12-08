package org.tuwaiq.recipes.repository

import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class UserRepository {
    var auth = Firebase.auth

    fun login(email: String, password: String): MutableLiveData<Boolean> {
        var mLiveData = MutableLiveData<Boolean>()
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    mLiveData.postValue(true)
                } else
                    mLiveData.postValue(false)
            }

        return mLiveData
    }

//    fun register(){}
}