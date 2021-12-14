package org.tuwaiq.recipes.view.home.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import org.tuwaiq.recipes.R
import org.tuwaiq.recipes.model.Recipe

class SearchAdapter(var data: List<Recipe>) : RecyclerView.Adapter<SearchAdapterHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchAdapterHolder {
        var v = LayoutInflater.from(parent.context).inflate(R.layout.list_row_search, null)
        return SearchAdapterHolder(v)
    }

    override fun onBindViewHolder(holder: SearchAdapterHolder, position: Int) {
        holder.name.text = data[position].title
        Picasso.get().load(data[position].image).into(holder.image)
    }

    override fun getItemCount(): Int {
        return data.size
    }

}

class SearchAdapterHolder(v: View) : RecyclerView.ViewHolder(v) {
    var image = v.findViewById<ImageView>(R.id.searchRowImage)
    var name = v.findViewById<TextView>(R.id.nameSearchRow)
}

