package com.example.restapi.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.restapi.R
import com.example.restapi.adapters.CommentsAdapter
import com.example.restapi.databinding.FragmentPostItemBinding
import com.example.restapi.models.comments.Comments
import com.example.restapi.models.posts.PostItem
import com.example.restapi.network.Result
import com.example.restapi.viewmodels.PostsViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog

class PostItemFragment(private val userPost: PostItem) : Fragment() {
    private lateinit var binding: FragmentPostItemBinding
    private lateinit var viewModel: PostsViewModel
    private lateinit var commentsAdapter: CommentsAdapter
    private lateinit var recyclerView: RecyclerView
    val postComment = MutableLiveData<Result<Comments>>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_post_item, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity())[PostsViewModel::class.java]
        binding.tvPostTitle.text = userPost.title
        binding.tvPostBody.text = userPost.body

        viewModel.getPostComment(userPost.id.toString(), postComment)

        binding.llComment.setOnClickListener{
            val dialog = BottomSheetDialog(requireContext(), R.style.BaseBottomSheetDialogTheme)
            val view: View = LayoutInflater.from(requireContext()).inflate(
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

        postComment.observe(viewLifecycleOwner){
            it?.let{
                commentsAdapter = CommentsAdapter(requireContext(), it.data!!)
                recyclerView.layoutManager = LinearLayoutManager(requireContext())
                recyclerView.adapter = commentsAdapter
            }
        }
    }

}
