package org.tuwaiq.recipes.view.home.recipes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import org.tuwaiq.recipes.databinding.ActivityHomeBinding
import org.tuwaiq.recipes.databinding.ActivityVegeRecipesBinding
import org.tuwaiq.recipes.model.Recipe
import org.tuwaiq.recipes.network.RecipeService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class VegeRecipesActivity : AppCompatActivity() {
    lateinit var binding: ActivityVegeRecipesBinding
    val vm: RecipesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVegeRecipesBinding.inflate(layoutInflater)

        var mRecyclerView = binding.vegeRecyclerView
        mRecyclerView.layoutManager = GridLayoutManager(this, 2)

        vm.getVege().observe(this,{
            mRecyclerView.adapter = RecipesAdapter(it)
        })
        setContentView(binding.root)
    }
}