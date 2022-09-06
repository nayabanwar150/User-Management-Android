package com.example.restapi.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.restapi.R
import com.example.restapi.adapters.CommentsAdapter
import com.example.restapi.databinding.ActivityPostBinding
import com.google.android.material.bottomsheet.BottomSheetDialog

class PostActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPostBinding
    private lateinit var commentsAdapter: CommentsAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_post)

        binding.llComment.setOnClickListener{
            Toast.makeText(this, "Bottomsheet", Toast.LENGTH_SHORT).show()
            val dialog = BottomSheetDialog(this@PostActivity, R.style.BaseBottomSheetDialogTheme)
            val view: View = LayoutInflater.from(this@PostActivity).inflate(
                R.layout.comment_bottomsheet,
                null,
                false
            )
            recyclerView = view.findViewById(R.id.rc_comments)
            dialog.setContentView(view)
            dialog.setCanceledOnTouchOutside(true)
            dialog.show()
            commentSetup()
        }
    }

    private fun commentSetup(){
        val cList = listOf("Nayab", "Abbas", "Saif", "Nawaz", "Rahul", "Deepak", "Rohan", "Sohan")

        commentsAdapter = CommentsAdapter(this@PostActivity, cList)
        recyclerView.layoutManager = LinearLayoutManager(this@PostActivity)
        recyclerView.adapter = commentsAdapter
    }
}