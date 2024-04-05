package com.example.got.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.got.R
import com.example.got.models.Characters
import com.example.got.models.Feed

class FeedAdapter(val feedList: List<Feed>):
    RecyclerView.Adapter<FeedAdapter.ViewHolder>() {

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var profileImg: ImageView
        var gotImage: ImageView
        var profileName: TextView
        var caption: TextView
        init {
            profileImg = itemView.findViewById(R.id.got_profile_image)
            gotImage = itemView.findViewById(R.id.got_image)
            profileName = itemView.findViewById(R.id.got_name)
            caption = itemView.findViewById(R.id.got_caption)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.feed_layout, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return feedList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var feedItems = feedList[position]
        holder.profileImg.setImageDrawable(holder.profileImg.context.getDrawable(feedItems.profileImage))
        holder.profileName.text = feedItems.profileName
        holder.gotImage.setImageDrawable(holder.gotImage.context.getDrawable(feedItems.gotImage))
        holder.caption.text = feedItems.caption
    }
}