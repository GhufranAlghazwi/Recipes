package org.tuwaiq.recipes.view.home.recipes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import org.tuwaiq.recipes.R
import org.tuwaiq.recipes.model.Recipe

class RecipesAdapter(var data: List<Recipe>) : RecyclerView.Adapter<RecipesAdapterHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipesAdapterHolder {
        var v = LayoutInflater.from(parent.context).inflate(R.layout.list_item_recipe, null)
        return RecipesAdapterHolder(v)
    }

    override fun onBindViewHolder(holder: RecipesAdapterHolder, position: Int) {
        holder.recipeName.text = data[position].title
        holder.time.text = data[position].readyInMinutes + " Min."
        Picasso.get().load(data[position].image).into(holder.recipeImg)
    }

    override fun getItemCount(): Int {
        return data.size
    }
}

class RecipesAdapterHolder(v: View) : RecyclerView.ViewHolder(v) {
    var recipeImg = v.findViewById<ImageView>(R.id.recipeImage)
    var recipeName = v.findViewById<TextView>(R.id.recipeNameTV)
    var time = v.findViewById<TextView>(R.id.preparationTimeTV)
}

