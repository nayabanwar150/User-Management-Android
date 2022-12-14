package com.example.restapi.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.restapi.R
import com.example.restapi.activities.UserActivity
import com.example.restapi.models.user.UsersItem
import java.io.Serializable

class UsersAdapter(private val context: Context,private val userList: List<UsersItem>): RecyclerView.Adapter<UsersAdapter.UsersViewHolder >(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
        val views = LayoutInflater.from(parent.context).inflate(R.layout.user_lists_tile, parent, false)
        return UsersViewHolder(views)
    }

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        val userAlphabet = textFromNameGenerator(userList[position].name)
        holder.username.text = userList[position].name
        holder.userAlpha.text = userAlphabet

        holder.userCard.setOnClickListener{
            context.startActivity(Intent(context, UserActivity::class.java).apply {
                putExtra("alphabet", userAlphabet)
                putExtra("user", userList[position] as Serializable)
            })
        }
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    inner class UsersViewHolder(viewItem: View): RecyclerView.ViewHolder(viewItem){
        val userCard: CardView = viewItem.findViewById(R.id.cv_user_tile)
        val username: TextView = viewItem.findViewById(R.id.tv_username)
        val userAlpha: TextView = viewItem.findViewById(R.id.tv_username_alpha)
    }

    private fun textFromNameGenerator(name: String): String{
        return name.elementAt(0).uppercase()
    }
}