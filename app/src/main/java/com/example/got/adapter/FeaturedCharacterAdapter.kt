package com.example.got.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.got.R
import com.example.got.models.Characters

class FeaturedCharacterAdapter(private val featdXterList: List<Characters>):
    RecyclerView.Adapter<FeaturedCharacterAdapter.ViewHolder>() {

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var characterImg: ImageView
        var characterName: TextView
        init {
            characterImg = itemView.findViewById(R.id.featuredCharacterImage)
            characterName = itemView.findViewById(R.id.character_name)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.featured_character_layout, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return featdXterList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var characters = featdXterList[position]
        if (characters.name == "")
        {
            if (characters.aliases[0] == "")
            {
                holder.characterName.text = "No Name"
            }
            else
                holder.characterName.text = characters.aliases[0]
        }
        else
        {
            holder.characterName.text = characters.name
        }
        holder.characterImg.setImageDrawable(holder.characterImg.context.getDrawable(R.drawable.royal_woman))
    }
}