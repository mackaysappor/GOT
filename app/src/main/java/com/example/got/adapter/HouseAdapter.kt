package com.example.got.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.got.R
import com.example.got.models.House
import com.example.got.utils.Common

class HouseAdapter(val housesList: List<House>, private val onItemClick: (House) -> Unit): RecyclerView.Adapter<HouseAdapter.ViewHolder>(){
    private lateinit var common: Common

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener{
        var houseShield: ImageView
        var houseName: TextView
        var houseRegion: TextView

        init {
            houseShield = itemView.findViewById(R.id.house_image)
            houseName = itemView.findViewById(R.id.house_name)
            houseRegion = itemView.findViewById(R.id.house_region)
            common = Common()
            itemView.setOnClickListener(this)

        }

        override fun onClick(v: View?){
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION){
                onItemClick(housesList[position])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.house_layout, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return housesList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val houses = housesList[position]
        holder.houseName.text = houses.name
        holder.houseRegion.text = houses.region
        common.setImage(holder.houseShield, houses.name)
    }
}