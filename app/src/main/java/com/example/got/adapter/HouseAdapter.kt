package com.example.got.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.got.R
import com.example.got.models.House

class HouseAdapter(val housesList: List<House>): RecyclerView.Adapter<HouseAdapter.ViewHolder>(){


    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var houseShield: ImageView
        var houseName: TextView
        var houseRegion: TextView

        init {
            houseShield = itemView.findViewById(R.id.house_image)
            houseName = itemView.findViewById(R.id.house_name)
            houseRegion = itemView.findViewById(R.id.house_region)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.house_layout, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return housesList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var houses = housesList[position]

        holder.houseName.text = houses.name
        holder.houseRegion.text = houses.region
        setHouseImage(holder, houses.name)
    }

    private fun setHouseImage(holder: ViewHolder, house: String){
        if (house.contains("arryn", true)){
            holder.houseShield.setImageDrawable(holder.houseShield.context.getDrawable(R.drawable.noun_house_arryn))
        }
        else if (house.contains("baratheon", true)){
            holder.houseShield.setImageDrawable(holder.houseShield.context.getDrawable(R.drawable.noun_house_baratheon))
        }
        else if (house.contains("frey", true)){
            holder.houseShield.setImageDrawable(holder.houseShield.context.getDrawable(R.drawable.noun_house_frey))
        }
        else if (house.contains("greyjoy", true)){
            holder.houseShield.setImageDrawable(holder.houseShield.context.getDrawable(R.drawable.noun_house_greyjoy))
        }
        else if (house.contains("lannister", true)){
            holder.houseShield.setImageDrawable(holder.houseShield.context.getDrawable(R.drawable.noun_house_lannister))
        }
        else if (house.contains("martell", true)){
            holder.houseShield.setImageDrawable(holder.houseShield.context.getDrawable(R.drawable.noun_house_martell))
        }
        else if (house.contains("mormont", true)){
            holder.houseShield.setImageDrawable(holder.houseShield.context.getDrawable(R.drawable.noun_house_mormont))
        }
        else if (house.contains("stark", true)){
            holder.houseShield.setImageDrawable(holder.houseShield.context.getDrawable(R.drawable.noun_house_stark))
        }
        else if (house.contains("targaryen", true)){
            holder.houseShield.setImageDrawable(holder.houseShield.context.getDrawable(R.drawable.noun_house_targaryen))
        }
        else if (house.contains("tully", true)){
            holder.houseShield.setImageDrawable(holder.houseShield.context.getDrawable(R.drawable.noun_house_tully))
        }
        else if (house.contains("tyrell", true)){
            holder.houseShield.setImageDrawable(holder.houseShield.context.getDrawable(R.drawable.noun_house_tyrell))
        }
        else{
            holder.houseShield.setImageDrawable(holder.houseShield.context.getDrawable(R.drawable.generic_house))
        }

    }
}