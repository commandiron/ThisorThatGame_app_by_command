package com.demirli.a53thisorthatgame

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.demirli.a53thisorthatgame.model.Image
import com.squareup.picasso.Picasso

class ScoreBoardAdapter(var imageList: List<Image>) : RecyclerView.Adapter<ScoreBoardAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.scoreboard_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = imageList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Picasso.get().load(imageList[position].photoUrl).into(holder.imageIv)
        holder.imageVote.setText("Vote: " + imageList[position].globalVote.toString())
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val imageIv = view.findViewById<ImageView>(R.id.image_iv)
        val imageVote = view.findViewById<TextView>(R.id.image_vote_tv)
    }
}