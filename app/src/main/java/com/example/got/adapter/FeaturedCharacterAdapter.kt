package com.example.got.adapter

import android.annotation.SuppressLint
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

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val characters = featdXterList[position]
        if (characters.name == "")
        {
            holder.characterName.text = characters.aliases[0].ifEmpty { "No Name" }
        }
        else
        {
            holder.characterName.text = characters.name
        }
        if (characters.gender == "Male")
        {
            holder.characterImg.setImageDrawable(holder.characterImg.context.getDrawable(R.drawable.character_male))
        }
        else if(characters.gender == "Female")
        {
            holder.characterImg.setImageDrawable(holder.characterImg.context.getDrawable(R.drawable.character_female))
        }
    }
}