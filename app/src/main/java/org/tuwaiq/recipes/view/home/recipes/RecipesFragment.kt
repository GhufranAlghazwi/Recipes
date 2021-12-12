package org.tuwaiq.recipes.view.home.recipes

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.tuwaiq.recipes.R
import org.tuwaiq.recipes.model.Recipe
import org.tuwaiq.recipes.network.RecipeService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RecipesFragment : Fragment() {
    val vm: RecipesViewModel by viewModels()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var v = inflater.inflate(R.layout.fragment_recipes, container, false)

        var vegeCard = v.findViewById<CardView>(R.id.vegCard1)
        vegeCard.setOnClickListener {
            startActivity(Intent(context,VegeRecipesActivity::class.java))
        }

        var mainCard = v.findViewById<CardView>(R.id.mainCard)
        mainCard.setOnClickListener {
            startActivity(Intent(context, MainDishesActivity::class.java))
        }

        var dessertCard = v.findViewById<CardView>(R.id.dessertCard)
        dessertCard.setOnClickListener {
            startActivity(Intent(context, DessertsActivity::class.java))
        }




        return v
    }

}