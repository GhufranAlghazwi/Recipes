package org.tuwaiq.recipes.view.recipeDetails

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.tuwaiq.recipes.databinding.ActivityRecipeDetailsBinding

class RecipeDetailsActivity : AppCompatActivity() {
    lateinit var binding: ActivityRecipeDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecipeDetailsBinding.inflate(layoutInflater)


        setContentView(binding.root)
    }
}