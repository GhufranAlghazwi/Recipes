package org.tuwaiq.recipes.view.home.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListAdapter
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.tuwaiq.recipes.R
import org.tuwaiq.recipes.model.Recipe
import org.tuwaiq.recipes.view.home.recipes.RecipesAdapter
import org.tuwaiq.recipes.view.home.recipes.RecipesViewModel
import org.tuwaiq.recipes.view.home.search.SearchAdapter


class SearchFragment : Fragment() {
    val vm: SearchViewModel by viewModels()
    lateinit var adapter: SearchAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var v = inflater.inflate(R.layout.fragment_search, container, false)

        val searchView = v.findViewById<SearchView>(R.id.mSearchView)

        val recyclerView = v.findViewById<RecyclerView>(R.id.searchRecyclerView)
        var recipesList = mutableListOf<String>()
        recyclerView.layoutManager = GridLayoutManager(context, 1)


        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                vm.search(newText).observe(viewLifecycleOwner,{
                    recyclerView.adapter = SearchAdapter(it)
                })
                return true
            }
        })


        return v
    }

//    fun setupSearchView(){
//        val searchView
//    }


}