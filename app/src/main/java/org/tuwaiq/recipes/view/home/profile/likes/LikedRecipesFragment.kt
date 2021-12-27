package org.tuwaiq.recipes.view.home.profile.likes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.tuwaiq.recipes.R

class LikedRecipesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var v = inflater.inflate(R.layout.fragment_liked_recipes, container, false)

        var recyclerView = v.findViewById<RecyclerView>(R.id.likesRecyclerView)
        recyclerView.layoutManager = GridLayoutManager(context, 2)

        return v
    }
}