package org.tuwaiq.recipes.view.editRecipe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.activity.viewModels
import com.squareup.picasso.Picasso
import org.tuwaiq.recipes.R
import org.tuwaiq.recipes.databinding.ActivityEditRecipeBinding
import org.tuwaiq.recipes.model.Recipe
import org.tuwaiq.recipes.util.Base64Helper
import org.tuwaiq.recipes.view.recipeDetails.RecipeDetailsActivity

class EditRecipeActivity : AppCompatActivity() {
    lateinit var binding: ActivityEditRecipeBinding
    val vm: EditRecipeViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditRecipeBinding.inflate(layoutInflater)

        var recipe =intent.getSerializableExtra("recipe") as Recipe

        if (Base64Helper.isBase64(recipe.image)){
            binding.editRecipeImage.setImageBitmap(Base64Helper.decodeImage(this, recipe.image))
        }
        else{
            Picasso.get().load(recipe.image).into(binding.editRecipeImage)
        }

        binding.editTextCategory.text = Editable.Factory.getInstance().newEditable(recipe.category)
        val categories = listOf("Main Dish", "Dessert", "Drink")
        val adapter = ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, categories)
        (binding.editCategory.editText as? AutoCompleteTextView)?.setAdapter(adapter)

        binding.editPrepTimeInput.text = Editable.Factory.getInstance().newEditable(recipe.readyInMinutes)
        binding.recipeNameInputEdit.text = Editable.Factory.getInstance().newEditable(recipe.title)
        binding.editIngrInput.text = Editable.Factory.getInstance().newEditable(recipe.ingredients)
        binding.editRecipeInstructionInput.text = Editable.Factory.getInstance().newEditable(recipe.instructions)

        binding.cancelEditingBtn.setOnClickListener {
            finish()
        }
        binding.editBtn.setOnClickListener {
            var title = binding.recipeNameInputEdit.text.toString()
//            var image =
            var time = binding.editPrepTimeInput.text.toString()
            var instructions = binding.editRecipeInstructionInput.text.toString()
            var category = binding.editTextCategory.text.toString()
            var ingr = binding.editIngrInput.text.toString()
            var uid = recipe.uid

            var updatedRecipe = Recipe(title, recipe.image, time, instructions, category, ingr, uid!!,"")
            vm.updateRecipe(recipe.id, updatedRecipe).observe(this, {
                if (it!=null){
                    Toast.makeText(this, "Recipe updated successfully", Toast.LENGTH_LONG).show()
                    var i = Intent(this, RecipeDetailsActivity::class.java)
                    i.putExtra("recipe", it)
                    startActivity(i)
                }
            })
        }
        setContentView(binding.root)
    }
}