package org.tuwaiq.recipes.view.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import org.tuwaiq.recipes.databinding.ActivitySplashBinding
import org.tuwaiq.recipes.view.MainActivity
import org.tuwaiq.recipes.view.home.HomeActivity
import org.tuwaiq.recipes.view.login.LoginActivity

class SplashActivity : AppCompatActivity() {
    lateinit var binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)

        Handler().postDelayed({
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        },2000)

        setContentView(binding.root)
    }
}