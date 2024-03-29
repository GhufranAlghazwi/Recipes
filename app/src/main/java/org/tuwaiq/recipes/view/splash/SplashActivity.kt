package org.tuwaiq.recipes.view.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import org.tuwaiq.recipes.databinding.ActivitySplashBinding
import org.tuwaiq.recipes.util.LocalizationHelper
import org.tuwaiq.recipes.util.SharedPreferenceHelper
import org.tuwaiq.recipes.view.home.mainscreen.HomeActivity

class SplashActivity : AppCompatActivity() {
    lateinit var binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)

        Handler().postDelayed({
            var language = SharedPreferenceHelper.getLanguage(this)
            if (language == "null")
                LocalizationHelper.changeLanguage(this, "en")
            else
                LocalizationHelper.changeLanguage(this, language)
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        },4000)

        setContentView(binding.root)
    }
}