package org.tuwaiq.recipes.repository

import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class UserRepository {
    var auth = Firebase.auth
    val db = Firebase.firestore

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

     fun register(name: String, email: String, password: String): MutableLiveData<Boolean> {
        var mLiveData = MutableLiveData<Boolean>()
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = hashMapOf(
                        "email" to auth.currentUser?.email.toString(),
                        "fullname" to name
                    )
                    db.collection("users").document(auth.currentUser?.uid.toString())
                        .set(user)
                }
                mLiveData.postValue(true)
            }.addOnFailureListener {
                println(it.message)
                mLiveData.postValue(false)
            }

        return mLiveData
    }

    fun resetPassword(email: String): MutableLiveData<Boolean>{
        var mLiveData = MutableLiveData<Boolean>()

        var mAuth: FirebaseAuth?
        mAuth = FirebaseAuth.getInstance()

        mAuth!!.sendPasswordResetEmail(email)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    mLiveData.postValue(true)
                } else {
                    mLiveData.postValue(false)
                }
            }

        return mLiveData
    }


}