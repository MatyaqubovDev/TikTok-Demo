package dev.matyaqubov.tiktukbyustabuva

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Adapter
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import dev.matyaqubov.tiktukbyustabuva.adapter.VideoAdapter
import dev.matyaqubov.tiktukbyustabuva.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var videos: ArrayList<Uri>
    private lateinit var videAdapter: VideoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        videos = ArrayList()

        startPickVideos()

    }

    private fun startPickVideos() {
        val intent = Intent(Intent.ACTION_GET_CONTENT, MediaStore.Video.Media.INTERNAL_CONTENT_URI)
        intent.type = "video/*"
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
        galleryLauncher.launch(intent)
    }

    private fun startShow(){
        videAdapter=VideoAdapter(videos)

        val pagerHelper=PagerSnapHelper()
        pagerHelper.attachToRecyclerView(binding.root)


        binding.root.apply {
            adapter=videAdapter
            layoutManager=LinearLayoutManager(this@MainActivity)
        }
    }

    val galleryLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == RESULT_OK) {
                for (i in 0 until it.data!!.clipData!!.itemCount){
                    videos.add(it.data!!.clipData!!.getItemAt(i).uri)
                    //videos.addAll(it.data.clipData)
                }
                startShow()
            }
        }
}