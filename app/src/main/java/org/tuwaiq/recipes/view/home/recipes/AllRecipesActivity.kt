package org.tuwaiq.recipes.view.home.recipes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import org.tuwaiq.recipes.databinding.ActivityAllRecipesBinding

class AllRecipesActivity : AppCompatActivity() {
    lateinit var binding: ActivityAllRecipesBinding
    val vm: RecipesViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAllRecipesBinding.inflate(layoutInflater)

        var mToolbar = binding.mToolBarAllRecipes
        mToolbar.title = "All Recipes"
        setSupportActionBar(mToolbar)
        mToolbar.setNavigationOnClickListener {
            finish()
        }

        binding.allRecipesRV.layoutManager = GridLayoutManager(this, 2)
        vm.getAll().observe(this, {
            binding.allRecipesRV.adapter = RecipesAdapter(it)
        })

        setContentView(binding.root)
    }
}