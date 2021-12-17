package org.tuwaiq.recipes.view.home.recipes

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import org.tuwaiq.recipes.R
import org.tuwaiq.recipes.model.Recipe
import org.tuwaiq.recipes.util.Base64Helper
import org.tuwaiq.recipes.view.recipeDetails.RecipeDetailsActivity
import java.util.*
import java.util.regex.Pattern

class RecipesAdapter(var data: List<Recipe>) : RecyclerView.Adapter<RecipesAdapterHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipesAdapterHolder {
        var v = LayoutInflater.from(parent.context).inflate(R.layout.list_item_recipe, null)
        return RecipesAdapterHolder(v)
    }

    override fun onBindViewHolder(holder: RecipesAdapterHolder, position: Int) {
        holder.recipeName.text = data[position].title
        holder.time.text = data[position].readyInMinutes + " Min."
        if (Base64Helper.isBase64(data[position].image)) {
            var image = Base64Helper.decodeImage(holder.recipeImg.context, data[position].image)
            Picasso.get().load(image).into(holder.recipeImg)
        }
        else {
            Picasso.get().load(data[position].image).into(holder.recipeImg)
        }

        holder.cardItem.setOnClickListener {
            var i = Intent(holder.cardItem.context, RecipeDetailsActivity::class.java)
            i.putExtra("recipe", data[position])
            holder.cardItem.context.startActivity(i)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}

class RecipesAdapterHolder(v: View) : RecyclerView.ViewHolder(v) {
    var recipeImg = v.findViewById<ImageView>(R.id.recipeImage)
    var recipeName = v.findViewById<TextView>(R.id.recipeNameTV)
    var time = v.findViewById<TextView>(R.id.preparationTimeTV)
    var cardItem = v.findViewById<CardView>(R.id.itemCard)
}

