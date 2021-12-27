package org.tuwaiq.recipes.view.home.profile.likes

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
import org.tuwaiq.recipes.model.LikedRecipe
import org.tuwaiq.recipes.util.Base64Helper
import org.tuwaiq.recipes.util.SharedPreferenceHelper
import org.tuwaiq.recipes.view.recipeDetails.RecipeDetailsActivity
import xyz.hanks.library.bang.SmallBangView

class LikesAdapter(var data: List<LikedRecipe>) : RecyclerView.Adapter<LikesAdapterHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LikesAdapterHolder {
        var v = LayoutInflater.from(parent.context).inflate(R.layout.list_item_recipe, null)
        return LikesAdapterHolder(v)
    }

    override fun onBindViewHolder(holder: LikesAdapterHolder, position: Int) {
        var uid = SharedPreferenceHelper.getUserID(holder.cardItem.context)
        holder.recipeName.text = data[position].title
        holder.time.text = data[position].readyInMinutes + " Min."
        if (Base64Helper.isBase64(data[position].image)) {
            var image = Base64Helper.decodeImage(holder.recipeImg.context, data[position].image)
            holder.recipeImg.setImageBitmap(image)
            //Picasso.get().load(image).into(holder.recipeImg)
        } else {
            Picasso.get().load(data[position].image).into(holder.recipeImg)
        }

        holder.cardItem.setOnClickListener {
            var i = Intent(holder.cardItem.context, RecipeDetailsActivity::class.java)
            i.putExtra("recipe1", data[position])
            i.putExtra("position", position)
            holder.cardItem.context.startActivity(i)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}

class LikesAdapterHolder(v: View) : RecyclerView.ViewHolder(v) {
    var recipeImg = v.findViewById<ImageView>(R.id.recipeImage)
    var recipeName = v.findViewById<TextView>(R.id.recipeNameTV)
    var time = v.findViewById<TextView>(R.id.preparationTimeTV)
    var cardItem = v.findViewById<CardView>(R.id.itemCard)
    var btnLike = v.findViewById<SmallBangView>(R.id.like_heart)
}

