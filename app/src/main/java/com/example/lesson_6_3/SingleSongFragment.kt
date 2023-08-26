package com.example.lesson_6_3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.lesson_6_3.databinding.FragmentSingleSongBinding

class SingleSongFragment : Fragment() {
    private lateinit var binding: FragmentSingleSongBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSingleSongBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvSongTitle.text = arguments?.getString("Когда не хватает слов, музыка говорит")
    }
}