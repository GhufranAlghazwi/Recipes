package org.tuwaiq.recipes.view.editRecipe

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.util.Base64
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.viewModels
import com.github.dhaval2404.imagepicker.ImagePicker
import com.squareup.picasso.Picasso
import org.tuwaiq.recipes.R
import org.tuwaiq.recipes.databinding.ActivityEditRecipeBinding
import org.tuwaiq.recipes.model.Recipe
import org.tuwaiq.recipes.util.Base64Helper
import org.tuwaiq.recipes.view.recipeDetails.RecipeDetailsActivity

class EditRecipeActivity : AppCompatActivity() {
    lateinit var binding: ActivityEditRecipeBinding
    lateinit var imagePicker: ImageView
    lateinit var encodedImage: String

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

        imagePicker= binding.editRecipeImage
        imagePicker.setOnClickListener {
            ImagePicker.with(this)
                .crop(1f,1f)
                .compress(32)
                .maxResultSize(1080,1080)
                .start()
        }

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

            var updatedRecipe = Recipe(title, encodedImage, time, instructions, category, ingr, uid!!,"")
            vm.updateRecipe(recipe.id, updatedRecipe).observe(this, {
                if (it!=null){
                    Toast.makeText(this, getString(R.string.recipe_updated), Toast.LENGTH_LONG).show()
                    var i = Intent(this, RecipeDetailsActivity::class.java)
                    i.putExtra("recipe", it)
                    startActivity(i)
                }
            })
        }
        setContentView(binding.root)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {

            val uri: Uri = data?.data!!
            imagePicker.setImageURI(uri)

            //encode
            encodedImage = Base64Helper.encodeImage(uri)

            //decode
            var bytes = Base64.decode(encodedImage, Base64.DEFAULT)
            var decodedImage = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)

        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(this, ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, getString(R.string.task_cancelled), Toast.LENGTH_SHORT).show()
        }
    }
}