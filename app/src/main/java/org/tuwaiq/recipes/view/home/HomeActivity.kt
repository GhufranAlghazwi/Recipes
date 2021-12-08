package org.tuwaiq.recipes.view.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.tuwaiq.recipes.R
import org.tuwaiq.recipes.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)


        setContentView(binding.root)
    }
}