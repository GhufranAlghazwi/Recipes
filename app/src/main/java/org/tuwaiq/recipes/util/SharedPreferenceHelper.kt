package org.tuwaiq.recipes.util

import android.content.Context
import android.view.textclassifier.TextLanguage
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

        fun saveLikesID(context: Context, id: String){
            var pref = context.getSharedPreferences("likesPref", Context.MODE_PRIVATE)
            pref.edit()
                .putString("lID", id).commit()
        }

        fun getLikesID(context: Context): String{
            var pref = context.getSharedPreferences("likesPref", Context.MODE_PRIVATE)
            var lID = pref.getString("lID", "null")
            return lID!!
        }

        fun saveLanguage(context: Context, language: String){
            var pref = context.getSharedPreferences("langPref", Context.MODE_PRIVATE)
            pref.edit()
                .putString("language", language).commit()
        }

        fun getLanguage(context: Context): String{
            var pref = context.getSharedPreferences("langPref", Context.MODE_PRIVATE)
            var lang = pref.getString("language", "null")
            return lang!!
        }
    }

}