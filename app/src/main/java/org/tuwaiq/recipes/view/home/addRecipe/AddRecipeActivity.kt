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
import androidx.core.widget.addTextChangedListener
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
import cn.pedant.SweetAlert.SweetAlertDialog
import org.tuwaiq.recipes.view.home.mainscreen.HomeActivity
import org.tuwaiq.recipes.view.home.profile.userprofile.ProfileFragment


class AddRecipeActivity : AppCompatActivity() {
    lateinit var binding: ActivityAddRecipeBinding
    var auth = Firebase.auth
    val vm: AddRecipeViewModel by viewModels()
    lateinit var imagePicker: ImageView
    lateinit var encodedImage: String
    lateinit var categoryChoice: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddRecipeBinding.inflate(layoutInflater)

        val categories = listOf(getString(R.string.main_dishes), getString(R.string.dessert), getString(R.string.drinks))
        val adapter = ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, categories)
        (binding.categoryMenu.editText as? AutoCompleteTextView)?.setAdapter(adapter)

        imagePicker= binding.addRecipeImage
        imagePicker.setOnClickListener {
            ImagePicker.with(this)
                .galleryOnly()
                .crop(1f,1f)
                .compress(32)
                .maxResultSize(620,620)
                .start()
        }

        var category = binding.editText.text.toString()
        binding.editText.addTextChangedListener {
            when (binding.editText.text.toString()){
                "Main Dishes", "أطباق رئيسية" -> categoryChoice = "Main Dish"
                "Dessert", "حلويات" -> categoryChoice = "Dessert"
                "Drinks", "مشروبات" -> categoryChoice = "Drink"
            }
        }

        binding.addButton.setOnClickListener {
            var title = binding.recipeNameInput.text.toString()
//            var image =
            var time = binding.prepTimeInput.text.toString()
            var instructions = binding.recipeInstructionInput.text.toString()
            var ingr = binding.ingrInput.text.toString()
            var uid = auth.currentUser?.uid

            var recipe = Recipe(title, encodedImage, time, instructions, categoryChoice, ingr, uid!!,"")
            vm.addRecipe(recipe).observe(this, {
                if (it!=null){
                    Toast.makeText(this, getString(R.string.recipe_added), Toast.LENGTH_LONG).show()
                    SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE)
                        .setTitleText("Good job!")
                        .setContentText(getString(R.string.recipe_added))
                        .setConfirmClickListener {
                            var i = Intent(this, HomeActivity::class.java)
                            i.putExtra("frgToLoad", "ProfileFragment")
                            //i.putExtra("recipe", recipe)
                            startActivity(i)
                        }
                        .show()

                }

            })
        }

        binding.cancelButton.setOnClickListener {
            finish()
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