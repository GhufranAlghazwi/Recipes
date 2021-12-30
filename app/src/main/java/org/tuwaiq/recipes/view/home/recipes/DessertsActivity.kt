package org.tuwaiq.recipes.view.home.recipes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import org.tuwaiq.recipes.databinding.ActivityDessertsBinding

class DessertsActivity : AppCompatActivity() {
    lateinit var binding: ActivityDessertsBinding
    val vm: RecipesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDessertsBinding.inflate(layoutInflater)

        var mToolbar = binding.mToolBarDessert
        mToolbar.title = "Dessert"
        setSupportActionBar(mToolbar)
        mToolbar.setNavigationOnClickListener {
            finish()
        }

        var mRecyclerView = binding.dessertRecyclerView
        mRecyclerView.layoutManager = GridLayoutManager(this, 2)

        vm.getDessert().observe(this,{
            mRecyclerView.adapter = RecipesAdapter(it)
        })


        setContentView(binding.root)
    }
}