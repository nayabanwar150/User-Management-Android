package com.example.restapi.fragments

import android.app.AlertDialog
import android.app.ProgressDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.restapi.R
import com.example.restapi.adapters.UsersAdapter
import com.example.restapi.databinding.FragmentUsersBinding
import com.example.restapi.viewmodels.UserViewModel
import com.example.restapi.network.Result
import com.example.restapi.utils.Util

class UsersFragment : Fragment() {
    private lateinit var binding: FragmentUsersBinding
    private lateinit var adapter: UsersAdapter
    private lateinit var viewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_users, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[UserViewModel::class.java]
        val dialog = Util.showDialog(requireContext())

        viewModel.users.observe(viewLifecycleOwner){

            when{
                it is Result.Loading -> {
                    dialog.show()
                }
                it is Result.Success && it.data != null -> {
                    // val userList = listOf<String>("Nayab Anwar", "Md Saifuddin", "Abrar Ahmad", "Nawaz Anwar", "Aiyaz Anwar","Mohammad Nayab Anwar", "Md Saifuddin", "Abrar Ahmad", "Nawaz Anwar", "Aiyaz Anwar")
                    val userList = it.data

                    adapter = UsersAdapter(requireContext(), userList)
                    binding.rcUserLists.layoutManager = GridLayoutManager(requireContext(), 2)
                    binding.rcUserLists.adapter = adapter

                    binding.tvUsersCount.text = it.data.size.toString()
                    dialog.hide()
                }
                else -> {
                    dialog.hide()
                }
            }
        }

    }
}
