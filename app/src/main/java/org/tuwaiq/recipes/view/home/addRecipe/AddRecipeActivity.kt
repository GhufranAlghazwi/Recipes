package org.tuwaiq.recipes.view.home.addRecipe

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.view.*
import android.widget.*
import androidx.activity.viewModels
import androidx.core.content.ContentProviderCompat.requireContext
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import org.tuwaiq.recipes.R
import org.tuwaiq.recipes.databinding.ActivityAddRecipeBinding
import org.tuwaiq.recipes.model.Recipe
import org.tuwaiq.recipes.util.Base64Helper
import org.tuwaiq.recipes.view.recipeDetails.RecipeDetailsActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.ByteArrayOutputStream

class AddRecipeActivity : AppCompatActivity() {
    lateinit var binding: ActivityAddRecipeBinding
    var auth = Firebase.auth
    val vm: AddRecipeViewModel by viewModels()
    lateinit var imagePicker: ImageView
    lateinit var encodedImage: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddRecipeBinding.inflate(layoutInflater)

        val categories = listOf("Main Dish", "Dessert", "Drink")
        val adapter = ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, categories)
        (binding.categoryMenu.editText as? AutoCompleteTextView)?.setAdapter(adapter)

        imagePicker= binding.addRecipeImage
        imagePicker.setOnClickListener {
            ImagePicker.with(this)
                .crop(1f,1f)
                .compress(50)
                .start()
        }

        binding.addButton.setOnClickListener {
            var title = binding.recipeNameInput.text.toString()
//            var image =
            var time = binding.prepTimeInput.text.toString()
            var instructions = binding.recipeInstructionInput.text.toString()
            var category = binding.editText.text.toString()
            var ingr = binding.ingrInput.text.toString()
            var uid = auth.currentUser?.uid

            var recipe = Recipe(title, encodedImage, time, instructions, category, ingr, uid!!,"")
            vm.addRecipe(recipe).observe(this, {
                if (it!=null){
                    Toast.makeText(this, "Recipe added", Toast.LENGTH_LONG).show()
                    var i = Intent(this, RecipeDetailsActivity::class.java)
                    i.putExtra("recipe", recipe)
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
            Toast.makeText(this, "Task Cancelled", Toast.LENGTH_SHORT).show()
        }
    }
}