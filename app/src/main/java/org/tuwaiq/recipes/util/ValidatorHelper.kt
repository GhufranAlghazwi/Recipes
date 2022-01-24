package org.tuwaiq.recipes.util

import android.util.Patterns

import android.text.TextUtils




class ValidatorHelper {

    companion object{

        fun validateEmail(email: String): Boolean{
            return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches()
        }

        fun validatePassword(pass: String): Boolean{
            return pass.length >= 8
        }
    }
}