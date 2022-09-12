package com.example.restapi.activities

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.restapi.R
import com.example.restapi.adapters.CommentsAdapter
import com.example.restapi.databinding.ActivityPostBinding
import com.example.restapi.fragments.PostItemFragment
import com.example.restapi.models.posts.Posts
import com.example.restapi.network.Result
import com.example.restapi.utils.Util
import com.example.restapi.viewmodels.PostsViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog

class PostActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPostBinding
    private lateinit var viewModel: PostsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        supportActionBar?.hide()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_post)

        viewModel = ViewModelProvider(this@PostActivity)[PostsViewModel::class.java]
        val dailog = Util.showDialog(this)

        viewModel.getUserPosts(intent.extras?.get("userId") as String)

        viewModel.userPost.observe(this@PostActivity){
            when{
                it is Result.Loading -> {
                    dailog.show()
                }
                it is Result.Success && it.data != null -> {
                    binding.vpPost.adapter = createAdapter(it.data)
                    dailog.hide()
                }
                else ->{
                    dailog.hide()
                }
            }
        }

    }

    private fun createAdapter(userPost: Posts): ViewPagerAdapter {
        var list = ArrayList<Fragment>()
        userPost.forEach{ it ->
            list.add(PostItemFragment(it))
        }
        return ViewPagerAdapter(this@PostActivity, list)
    }
}

class ViewPagerAdapter(fragmentActivity: FragmentActivity, l: ArrayList<Fragment>) :
    FragmentStateAdapter(fragmentActivity) {
    private val list = l
    override fun getItemCount(): Int {
        return list.size
    }

    override fun createFragment(position: Int): Fragment {
        return list[position]
    }
}