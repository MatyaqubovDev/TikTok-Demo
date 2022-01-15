package dev.matyaqubov.tiktukbyustabuva.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.matyaqubov.tiktukbyustabuva.databinding.ItemVideoBinding

class VideoAdapter(private val videos:ArrayList<Uri>) :RecyclerView.Adapter<VideoAdapter.VideoViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val binding=ItemVideoBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return VideoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {

        holder.binding.apply {
            tvItemTitle.text="Video $position"
            vvItemVideo.setVideoURI(videos[position])
            vvItemVideo.start()
        }

    }

    override fun getItemCount(): Int = videos.size


    class VideoViewHolder(val binding: ItemVideoBinding):RecyclerView.ViewHolder(binding.root)
}