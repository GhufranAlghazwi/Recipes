package org.tuwaiq.recipes.view.home.recipes

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Button
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.fragment.app.viewModels
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import org.tuwaiq.recipes.R
import org.tuwaiq.recipes.util.LocalizationHelper
import org.tuwaiq.recipes.util.SharedPreferenceHelper
import org.tuwaiq.recipes.view.home.mainscreen.HomeActivity


class RecipesFragment : Fragment() {
    val vm: RecipesViewModel by viewModels()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
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

        var allRecipesAdapter = v.findViewById<Button>(R.id.AllRecipesBtn)
            .setOnClickListener {
                startActivity(Intent(context, AllRecipesActivity::class.java))
            }




        return v
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.profile_toolbar, menu)
        menu.findItem(R.id.logout).isVisible = false
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.localize -> {
                if(SharedPreferenceHelper.getLanguage(requireContext())=="en"){
                    LocalizationHelper.changeLanguage(context as Activity,"ar")
                    SharedPreferenceHelper.saveLanguage(requireContext(),"ar")
                }

                else{
                    LocalizationHelper.changeLanguage(context as Activity,"en")
                    SharedPreferenceHelper.saveLanguage(requireContext(),"en")

                }
            }
        }
        return super.onOptionsItemSelected(item)
    }
}