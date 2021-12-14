package org.tuwaiq.recipes.view.home.addRecipe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.tuwaiq.recipes.databinding.ActivityAddRecipeBinding

class AddRecipeActivity : AppCompatActivity() {
    lateinit var binding: ActivityAddRecipeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddRecipeBinding.inflate(layoutInflater)


        setContentView(binding.root)
    }
}