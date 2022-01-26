package org.tuwaiq.recipes.view.home.profile.myrecipes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import org.tuwaiq.recipes.R
import org.tuwaiq.recipes.model.Recipe
import org.tuwaiq.recipes.view.home.profile.userprofile.ProfileViewModel
import org.tuwaiq.recipes.view.home.recipes.RecipesAdapter

class MyRecipesFragment : Fragment() {
    val vm: ProfileViewModel by viewModels()
    var currentUser = Firebase.auth.uid

    var recipesList = mutableListOf<Recipe>()

    var myAdapter = RecipesAdapter(recipesList)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var v = inflater.inflate(R.layout.fragment_my_recipes, container, false)
        var recyclerView = v.findViewById<RecyclerView>(R.id.myRecipesRV)
        recyclerView.layoutManager = GridLayoutManager(context, 2)
        recyclerView.adapter = myAdapter

        vm.getUserRecipes(currentUser!!).observeForever{
            recipesList.clear()
            recipesList.addAll(it)
            myAdapter.notifyDataSetChanged()
        }
        return v
    }

}