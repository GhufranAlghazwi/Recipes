package org.tuwaiq.recipes.view.home.recipes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import org.tuwaiq.recipes.databinding.ActivityMainDishesBinding
import org.tuwaiq.recipes.model.Recipe
import org.tuwaiq.recipes.network.RecipeService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainDishesActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainDishesBinding
    val vm: RecipesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainDishesBinding.inflate(layoutInflater)

        var mRecyclerView = binding.mainDishesRecyclerView
        mRecyclerView.layoutManager = GridLayoutManager(this, 2)

        vm.getMain().observe(this,{
            mRecyclerView.adapter = RecipesAdapter(it)
        })

        setContentView(binding.root)
    }
}
