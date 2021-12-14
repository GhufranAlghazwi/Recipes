package org.tuwaiq.recipes.view.home.recipes

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.fragment.app.viewModels
import org.tuwaiq.recipes.R


class RecipesFragment : Fragment() {
    val vm: RecipesViewModel by viewModels()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var v = inflater.inflate(R.layout.fragment_recipes, container, false)

        var vegeCard = v.findViewById<CardView>(R.id.drinksCard)
        vegeCard.setOnClickListener {
            startActivity(Intent(context,DrinksActivity::class.java))
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