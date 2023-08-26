package com.example.lesson_6_3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import com.example.lesson_6_3.databinding.FragmentPlaylistBinding

class PlaylistFragment : Fragment() {
    private lateinit var binding: FragmentPlaylistBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPlaylistBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val adapter = SongsAdapter(uploadSongs(), this::onItemClick)
        binding.rvSongs.adapter = adapter
        super.onViewCreated(view, savedInstanceState)
    }

    private fun uploadSongs() = listOf(
        Music("1", "Jax 02.14", "БСББ", "3:28"),
        Music("2", "Скриптонит", "Лухари", "2:44"),
        Music("3", "Travis Scott", "Circus Maximus", "4:19"),
        Music("4", "Swedish House Mafia", "Moth To A Flame", "3:54"),
        Music("5", "Timati ft. Mario Winans", "Forever", "4:27"),
    )

    private fun onItemClick(title: String) {
        requireActivity().supportFragmentManager.findFragmentById(R.id.album_fragment)?.view?.isGone =
            true
        val bundle = Bundle()
        bundle.putString("Когда не хватает слов, музыка говорит", title)
        val singleSongFragment = SingleSongFragment()
        singleSongFragment.arguments = bundle
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.songs_fragment, singleSongFragment).commit()
    }
}