package org.tuwaiq.recipes.view.home.recipes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import org.tuwaiq.recipes.databinding.ActivityDrinksBinding

class DrinksActivity : AppCompatActivity() {
    lateinit var binding: ActivityDrinksBinding
    val vm: RecipesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDrinksBinding.inflate(layoutInflater)

        var mRecyclerView = binding.vegeRecyclerView
        mRecyclerView.layoutManager = GridLayoutManager(this, 2)

        vm.getDrinks().observe(this,{
            mRecyclerView.adapter = RecipesAdapter(it)
        })
        setContentView(binding.root)
    }
}