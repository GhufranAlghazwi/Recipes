package org.tuwaiq.recipes.view.recipeDetails

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import com.squareup.picasso.Picasso
import org.tuwaiq.recipes.databinding.ActivityRecipeDetailsBinding
import org.tuwaiq.recipes.model.Recipe

class RecipeDetailsActivity : AppCompatActivity() {
    lateinit var binding: ActivityRecipeDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecipeDetailsBinding.inflate(layoutInflater)
        var expandableIngr = binding.expandablengr
        var cardViewIngr = binding.cardViewIngr

        var recipe = intent.getSerializableExtra("recipe") as Recipe

        Picasso.get().load(recipe.image).into(binding.imageViewDetails)
        binding.textViewDetailsName.text = recipe.title
        binding.textViewDetailsIngr.text = "Ingredients\n"+recipe.ingredients
        binding.textViewDetailsInstructions.text = "Instructions\n${recipe.instructions}"

        cardViewIngr.setOnClickListener {
            if(!expandableIngr.isVisible){
                expandableIngr.isVisible=true
                binding.expandableIngrTV.text = recipe.ingredients
            }
            else{
                expandableIngr.isVisible=false
            }



        }
        setContentView(binding.root)
    }
}