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
import android.widget.AdapterView.OnItemClickListener
import org.tuwaiq.recipes.model.LikedRecipe
import org.tuwaiq.recipes.model.Likes
import org.tuwaiq.recipes.util.SharedPreferenceHelper
import org.tuwaiq.recipes.view.home.profile.likes.LikesViewModel
import xyz.hanks.library.bang.SmallBangView
import kotlin.collections.ArrayList


class RecipesAdapter(var data: List<Recipe>) : RecyclerView.Adapter<RecipesAdapterHolder>() {
    private var dataList: MutableList<Recipe> = data as MutableList<Recipe>
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipesAdapterHolder {
        var v = LayoutInflater.from(parent.context).inflate(R.layout.list_item_recipe, null)
        return RecipesAdapterHolder(v)
    }

    override fun onBindViewHolder(holder: RecipesAdapterHolder, position: Int) {
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
            i.putExtra("recipe", data[position])
            i.putExtra("position", position)
            holder.cardItem.context.startActivity(i)
        }

        holder.btnLike.setOnClickListener {
                if(SharedPreferenceHelper.getLikesID(holder.btnLike.context) == "null"){
                    LikesViewModel().createLikesList(uid, Likes("", uid)).observeForever {
                        if (it != null){
                            SharedPreferenceHelper.saveLikesID(holder.btnLike.context, it.id)
                            var data= data[position]
                            var likedRecipe = LikedRecipe(data.category, "", data.image, data.ingredients, data.instructions, it.id, data.readyInMinutes, data.title, uid)
                            LikesViewModel().addToLikes(uid, it.id, likedRecipe).observeForever {
                                holder.btnLike.isSelected = it
                            }
                        }
                    }
                } else{
                    var lid = SharedPreferenceHelper.getLikesID(holder.btnLike.context)
                    var data= data[position]
                    var likedRecipe = LikedRecipe(data.category, "", data.image, data.ingredients, data.instructions, lid, data.readyInMinutes, data.title, uid)
                    LikesViewModel().addToLikes(uid, lid, likedRecipe).observeForever {
                        holder.btnLike.isSelected = it
                    }
                }
            if (holder.btnLike.isSelected()) {
                holder.btnLike.setSelected(false)
            } else {
                holder.btnLike.setSelected(true)
            }
            }
        }


    override fun getItemCount(): Int {
        return data.size
    }

    fun removeAt(position: Int) {
        dataList.removeAt(position)
        notifyItemRemoved(position)
        notifyDataSetChanged()
    }
}

class RecipesAdapterHolder(v: View) : RecyclerView.ViewHolder(v) {
    var recipeImg = v.findViewById<ImageView>(R.id.recipeImage)
    var recipeName = v.findViewById<TextView>(R.id.recipeNameTV)
    var time = v.findViewById<TextView>(R.id.preparationTimeTV)
    var cardItem = v.findViewById<CardView>(R.id.itemCard)
    var btnLike = v.findViewById<SmallBangView>(R.id.like_heart)
}

