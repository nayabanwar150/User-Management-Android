package com.example.restapi.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.restapi.R
import com.example.restapi.databinding.ActivityUserBinding

class UserActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUserBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_user)

        binding.tvUserName.text = intent?.extras?.getString("username")
        binding.tvUserNameAlpha.text = intent?.extras?.getString("alphabet")

        binding.clLoadMorePost.setOnClickListener{
            startActivity(Intent(this@UserActivity, PostActivity::class.java).apply {
                putExtra("post_title", "title")
                putExtra("post_body", "body")
            })
        }

        binding.clLoadMorePhotos.setOnClickListener {
            Toast.makeText(this@UserActivity, "Coming soon", Toast.LENGTH_SHORT).show()
        }

        binding.clLoadMoreTodos.setOnClickListener {
            Toast.makeText(this@UserActivity, "Coming soon", Toast.LENGTH_SHORT).show()
        }
    }
}