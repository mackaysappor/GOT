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

class CharacterAdapter(private val charactersList: List<Characters>, private val onItemClick: (Characters) -> Unit):
    RecyclerView.Adapter<CharacterAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener{
        var characterName: TextView
        var characterTitle: TextView
        var characterImage: ImageView

        init {
            characterName = itemView.findViewById(R.id.txt_character_name)
            characterTitle = itemView.findViewById(R.id.txt_character_title_alias)
            characterImage = itemView.findViewById(R.id.character_image_view)
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?){
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION){
                onItemClick(charactersList[position])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.character_layout, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return charactersList.size
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val characters = charactersList[position]
        //holder.characterName.text = characters.name

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
            holder.characterImage.setImageDrawable(holder.characterImage.context.getDrawable(R.drawable.character_male))
        }
        else if(characters.gender == "Female")
        {
            holder.characterImage.setImageDrawable(holder.characterImage.context.getDrawable(R.drawable.character_female))
        }
    }
}