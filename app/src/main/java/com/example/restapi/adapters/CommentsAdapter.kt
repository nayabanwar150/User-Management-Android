package com.example.restapi.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.restapi.R

class CommentsAdapter(private  val context: Context, private val cList: List<String>): RecyclerView.Adapter<CommentsAdapter.CommentsViewHolder>() {

    inner class CommentsViewHolder(viewItem: View): RecyclerView.ViewHolder(viewItem){
        val userName: TextView = viewItem.findViewById(R.id.tv_comment_username)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.comment_item, parent, false)
        return CommentsViewHolder(view)
    }

    override fun onBindViewHolder(holder: CommentsViewHolder, position: Int) {
        holder.userName.text = cList[position]
    }

    override fun getItemCount(): Int {
        return cList.size
    }
}