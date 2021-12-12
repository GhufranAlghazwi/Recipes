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

        var retorfit = Retrofit.Builder()
            .baseUrl("https://61b4d1230e84b70017331990.mockapi.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        var productService = retorfit.create(RecipeService::class.java)
        productService.getVegRecipes().enqueue(object : Callback<List<Recipe>> {
            override fun onResponse(
                call: Call<List<Recipe>>,
                response: Response<List<Recipe>>
            ) {
                var list = response.body()
                mRecyclerView.adapter=RecipesAdapter(list!!)
            }

            override fun onFailure(call: Call<List<Recipe>>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })

        setContentView(binding.root)
    }
}