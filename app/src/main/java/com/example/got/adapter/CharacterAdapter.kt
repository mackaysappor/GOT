package com.example.got.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.got.R
import com.example.got.models.Characters

class CharacterAdapter(private val charactersList: List<Characters>):
    RecyclerView.Adapter<CharacterAdapter.ViewHolder>() {

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var characterName: TextView
        var characterTitle: TextView
        var characterImage: ImageView

        init {
            characterName = itemView.findViewById(R.id.txt_character_name)
            characterTitle = itemView.findViewById(R.id.txt_character_title_alias)
            characterImage = itemView.findViewById(R.id.character_image_view)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.character_layout, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return charactersList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val characters = charactersList[position]
        //holder.characterName.text = characters.name

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

        /*if (characters.titles[0] == "")
        {
            holder.characterTitle.text = characters.aliases[0]
        }
        else
        {
            holder.characterTitle.text = characters.titles[0]
        }*/

        if (characters.gender == "Male")
        {
            holder.characterImage.setImageDrawable(holder.characterImage.context.getDrawable(R.drawable.male_image))
        }
        else if(characters.gender == "Female")
        {
            holder.characterImage.setImageDrawable(holder.characterImage.context.getDrawable(R.drawable.regular_woman))
        }
    }
}