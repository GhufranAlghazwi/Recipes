package org.tuwaiq.recipes.util

import android.content.Context
import androidx.appcompat.app.AppCompatActivity

class SharedPreferenceHelper {

    companion object {
        fun saveUserID(context: Context, uid: String){
            var pref = context.getSharedPreferences("MyPref", Context.MODE_PRIVATE)
            pref.edit()
                .putString("id", uid).commit()
        }

        fun getUserID(context: Context): String{
            var pref = context.getSharedPreferences("MyPref", Context.MODE_PRIVATE)
            var id = pref.getString("id","null")
            return id!!
        }
    }

}