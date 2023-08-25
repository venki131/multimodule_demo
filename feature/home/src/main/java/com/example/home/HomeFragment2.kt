package com.example.home

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.home.databinding.FragmentHome2Binding


class HomeFragment2 : Fragment() {

    private lateinit var binding: FragmentHome2Binding
    private var button: Button? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHome2Binding.inflate(inflater, container, false)
        button = binding.btn
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        button?.setOnClickListener {
            findNavController().navigate(Uri.parse("example://favourites"))

        }
    }
}