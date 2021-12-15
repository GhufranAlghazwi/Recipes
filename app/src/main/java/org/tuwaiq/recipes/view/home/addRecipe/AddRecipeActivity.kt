package org.tuwaiq.recipes.view.home.addRecipe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.content.ContentProviderCompat.requireContext
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import org.tuwaiq.recipes.R
import org.tuwaiq.recipes.databinding.ActivityAddRecipeBinding

class AddRecipeActivity : AppCompatActivity() {
    lateinit var binding: ActivityAddRecipeBinding
    var auth = Firebase.auth
    val vm: AddRecipeViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddRecipeBinding.inflate(layoutInflater)

        val categories = listOf("Main Dish", "Dessert", "Drink")
        val adapter = ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, categories)
        (binding.categoryMenu.editText as? AutoCompleteTextView)?.setAdapter(adapter)

        binding.addButton.setOnClickListener {
            var title = binding.recipeNameInput.text.toString()
            var image = ""
            var time = binding.prepTimeInput.text.toString()
            var instructions = binding.recipeInstructionInput.text.toString()
            var category = binding.editText.text.toString()
            var ingr = binding.ingrInput.text.toString()
            var uid = auth.currentUser?.uid

            vm.addRecipe(title,image,time,instructions,category,ingr, uid!!).observe(this, {
                if (it)
                    Toast.makeText(this, "Recipe", Toast.LENGTH_LONG).show()
            })
        }


        setContentView(binding.root)
    }
}