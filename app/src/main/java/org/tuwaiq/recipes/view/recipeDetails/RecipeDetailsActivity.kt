package org.tuwaiq.recipes.view.recipeDetails

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.squareup.picasso.Picasso
import org.tuwaiq.recipes.R
import org.tuwaiq.recipes.databinding.ActivityRecipeDetailsBinding
import org.tuwaiq.recipes.model.Recipe
import org.tuwaiq.recipes.util.Base64Helper
import cn.pedant.SweetAlert.SweetAlertDialog
import org.tuwaiq.recipes.view.home.recipes.RecipesAdapter


class RecipeDetailsActivity : AppCompatActivity() {
    lateinit var binding: ActivityRecipeDetailsBinding
    var currentUser = Firebase.auth.currentUser
    val vm: DetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecipeDetailsBinding.inflate(layoutInflater)
        var expandableIngr = binding.expandablengr
        var cardViewIngr = binding.cardViewIngr
        var list = mutableListOf<Recipe>() as List<Recipe>
        var myAdapter = RecipesAdapter(list)

        var recipe = intent.getSerializableExtra("recipe") as Recipe
        //var recipe1 = intent.getSerializableExtra("recipe1") as LikedRecipe
        var position = intent.getIntExtra("position", 0)

        if (currentUser?.uid == recipe.uid) {
            binding.editButton.isVisible = true
            binding.deleteButton.isVisible = true
            binding.deleteButton.setOnClickListener {
                SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                    .setTitleText("Are you sure?")
                    .setContentText("Won't be able to recover this recipe!")
                    .setConfirmText("Yes,delete it!")
                    .setConfirmClickListener { sDialog ->
                        vm.deleteRecipe(recipe.id).observe(this, {
                            if (it) {
                                sDialog
                                    .setTitleText("Deleted!")
                                    .setContentText("Your recipe has been deleted!")
                                    .setConfirmText("OK")
                                    .setConfirmClickListener{
                                        finish()
                                        myAdapter.removeAt(position)
                                    }
                                    .changeAlertType(SweetAlertDialog.SUCCESS_TYPE)
                            } else {
                                Toast.makeText(this, "Failed to delete", Toast.LENGTH_SHORT).show()
                            }

                        })
                    }
                    .show()
            }
        }

        //Picasso.get().load(recipe.image).into(binding.imageViewDetails)
        if (Base64Helper.isBase64(recipe.image)) {
            var image = Base64Helper.decodeImage(this, recipe.image)
            binding.imageViewDetails.setImageBitmap(image)
            //Picasso.get().load(image).into(binding.imageViewDetails)
        } else {
            Picasso.get().load(recipe.image).into(binding.imageViewDetails)
        }
        binding.textViewDetailsName.text = recipe.title
        binding.textViewPrepTime.text = recipe.readyInMinutes + " Min."

        binding.expandableIngrTV.text = recipe.ingredients
        cardViewIngr.setOnClickListener {
            expandableIngr.isVisible = !expandableIngr.isVisible
            if (!expandableIngr.isVisible) {
                binding.imageView6.setImageResource(R.drawable.arrow_right)
            } else
                binding.imageView6.setImageResource(R.drawable.down_arrow)
        }


        binding.expandableInstructionsTV.text = recipe.instructions
        binding.cardViewInstruction.setOnClickListener {
            binding.expandablInstruction.isVisible = !binding.expandablInstruction.isVisible
            if (!binding.expandablInstruction.isVisible) {
                binding.imageView7.setImageResource(R.drawable.arrow_right)
            } else
                binding.imageView7.setImageResource(R.drawable.down_arrow)
        }
        setContentView(binding.root)
    }
}