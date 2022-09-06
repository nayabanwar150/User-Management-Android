package com.example.restapi.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.example.restapi.R
import com.example.restapi.adapters.UsersAdapter
import com.example.restapi.databinding.FragmentUsersBinding

class UsersFragment : Fragment() {
    private lateinit var binding: FragmentUsersBinding
    private lateinit var adapter: UsersAdapter

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

        val userList = listOf<String>("Nayab Anwar", "Md Saifuddin", "Abrar Ahmad", "Nawaz Anwar", "Aiyaz Anwar","Mohammad Nayab Anwar", "Md Saifuddin", "Abrar Ahmad", "Nawaz Anwar", "Aiyaz Anwar")

        adapter = UsersAdapter(requireContext(), userList)
        binding.rcUserLists.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.rcUserLists.adapter = adapter

    }
}
