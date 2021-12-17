package org.tuwaiq.recipes.view.recipeDetails

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import com.squareup.picasso.Picasso
import org.tuwaiq.recipes.databinding.ActivityRecipeDetailsBinding
import org.tuwaiq.recipes.model.Recipe
import org.tuwaiq.recipes.util.Base64Helper

class RecipeDetailsActivity : AppCompatActivity() {
    lateinit var binding: ActivityRecipeDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecipeDetailsBinding.inflate(layoutInflater)
        var expandableIngr = binding.expandablengr
        var cardViewIngr = binding.cardViewIngr

        var recipe = intent.getSerializableExtra("recipe") as Recipe

        Picasso.get().load(recipe.image).into(binding.imageViewDetails)
        if (Base64Helper.isBase64(recipe.image)) {
            var image = Base64Helper.decodeImage(this, recipe.image)
            Picasso.get().load(image).into(binding.imageViewDetails)
        }
        else {
            Picasso.get().load(recipe.image).into(binding.imageViewDetails)
        }
        binding.textViewDetailsName.text = recipe.title
        binding.textViewPrepTime.text = recipe.readyInMinutes + " Min."

        binding.expandableIngrTV.text = recipe.ingredients
        cardViewIngr.setOnClickListener {
            expandableIngr.isVisible = !expandableIngr.isVisible
        }

        binding.expandableInstructionsTV.text = recipe.instructions
        binding.cardViewInstruction.setOnClickListener {
            binding.expandablInstruction.isVisible = !binding.expandablInstruction.isVisible
        }
        setContentView(binding.root)
    }
}