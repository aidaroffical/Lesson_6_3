package com.example.lesson_6_3

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.lesson_6_3.databinding.ItemSongBinding

class SongsAdapter(
    private val list: List<Music>,
    private val onItemClick: (title: String) -> Unit
) : Adapter<SongsAdapter.SongsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = SongsViewHolder(
        ItemSongBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: SongsViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount() = list.size

    inner class SongsViewHolder(private val binding: ItemSongBinding) : ViewHolder(binding.root) {
        fun onBind(music: Music) = with(binding) {
            tvRank.text = music.rank
            tvTitle.text = music.title
            tvAuthor.text = music.author
            tvDuration.text = music.duration

            Glide.with(itemView)
                .load(getImageUrlForMusic(music))
                .into(binding.imageView)


            root.setOnClickListener {
                onItemClick(list[adapterPosition].title)
            }
        }

        private fun getImageUrlForMusic(music: Music): String {
            return when (music.rank) {
                "1" -> "https://i.ytimg.com/vi/f09jqPAEaDw/maxresdefault.jpg"
                "2" -> "https://i1.sndcdn.com/artworks-oFXDzjgYpm97-0-t500x500.jpg"
                "3" -> "https://media.pitchfork.com/photos/64c3bee4a7c2659c4cdcf382/1:1/w_3000,h_3000,c_limit/Travis%20Scott%20-%20Utopia.jpeg"
                "4" -> "https://images.genius.com/8c4463318e62793b58e634ce42748b1a.1000x1000x1.png"
                "5" -> "https://images.genius.com/631711870c2e08ffcea49de2df63d052.600x600x1.jpg"
                else -> ""
            }
        }
    }
}