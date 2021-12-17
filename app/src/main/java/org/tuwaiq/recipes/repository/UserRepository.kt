package org.tuwaiq.recipes.repository

import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import org.tuwaiq.recipes.model.User
import org.tuwaiq.recipes.network.API
import org.tuwaiq.recipes.network.UserService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepository {
    var auth = Firebase.auth
    val db = Firebase.firestore
    val userService= API.getInstance().create(UserService::class.java)

    fun login(email: String, password: String): MutableLiveData<String> {
        var mLiveData = MutableLiveData<String>()
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    mLiveData.postValue(auth.currentUser?.uid)
                }
                else{
                    mLiveData.postValue("")
                }
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

    fun addUserToAPI(user: User): MutableLiveData<User>{
        var mLiveData = MutableLiveData<User>()
        userService.addUser(user).enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if(response.isSuccessful)
                    mLiveData.postValue(response.body())
                else mLiveData.postValue(User("","",""))
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })

        return mLiveData
    }

    fun resetPassword(email: String): MutableLiveData<Boolean> {
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

    fun checkLogin(user: FirebaseUser?): MutableLiveData<Boolean> {
        var mLiveData = MutableLiveData<Boolean>()
//        var user = auth.currentUser
        if (user == null){
            mLiveData.postValue(false)
        }
        else{
            mLiveData.postValue(true)
        }
        return mLiveData
    }

    fun getUserByFbID(fbID: String): MutableLiveData<List<User>>{
        var mLiveData = MutableLiveData<List<User>>()

        userService.getUserByFBID(fbID).enqueue(object : Callback<List<User>> {
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                mLiveData.postValue(response.body())
            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })

        return mLiveData
    }


}