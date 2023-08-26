package com.example.lesson_6_3

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
        val adapter = SongsAdapter(uploadSongs()) { title ->
            onItemClick(title)
        }

        binding.rvSongs.adapter = adapter
        super.onViewCreated(view, savedInstanceState)
    }

    private fun uploadSongs() = listOf(
        Music("1","Jax 02.14", "БСББ", "3:28", ),
        Music("2","Скриптонит", "Лухари", "2:44"),
        Music("3","Travis Scott", "Circus Maximus", "4:19"),
        Music("4","Swedish House Mafia", "Moth To A Flame", "3:54"),
        Music("5","Timati ft. Mario Winans", "Forever", "4:27"),
        Music("6","Phantogram", "Black Out Days", "3:47"),
        Music("7","Freeman", "Baylandym", "2:45"),
        Music("8","The Weeknd", "Call out my name", "3:48"),
        Music("9","Jax, Nel 02.14", "Koka lova", "3:47"),
        Music("10","Кот Балу, Ulukmanapo, K-Chante", "Среди сотен дорог", "2:33"),
        Music("11","Ulukmanapo feat. Бегиш", "Любовь Как Сон", "3:11"),

    )


    private fun onItemClick(title: String) {
        val bundle = Bundle()
        bundle.putString("Когда не хватает слов, музыка говорит", title)
        val singleSongFragment = SingleSongFragment()
        singleSongFragment.arguments = bundle

        val intent = Intent(requireContext(), AudioService::class.java)
        requireActivity().startService(intent)

        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.album_fragment, singleSongFragment)
            .addToBackStack(null)
            .commit()
    }


    override fun onDestroy() {
        super.onDestroy()
        val intent = Intent(requireContext(), AudioService::class.java)
        requireActivity().stopService(intent)
    }
}