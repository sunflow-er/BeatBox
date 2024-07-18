package org.javaapp.beatbox

import android.os.Bundle
import android.view.ViewGroup
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.javaapp.beatbox.databinding.ActivityMainBinding
import org.javaapp.beatbox.databinding.ListItemSoundBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // 바인딩
        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main) // 액티비티의 레이아웃을 설정하고, 바인딩 클래스 인스턴스 생성
        
        // 리사이클러 뷰
        binding.recyclerView.apply {
            layoutManager = GridLayoutManager(context, 3)
            adapter = SoundAdapter()
        }
    }

    private inner class SoundHolder(private val binding: ListItemSoundBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    private inner class SoundAdapter() : RecyclerView.Adapter<SoundHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SoundHolder {
            // ListItemSoundBinding 바인딩 클래스의 인스턴스 생성 및 참조
            val binding = DataBindingUtil.inflate<ListItemSoundBinding>(layoutInflater, R.layout.list_item_sound, parent,false)

            return SoundHolder(binding)
        }

        override fun getItemCount(): Int {
            return 0
        }

        override fun onBindViewHolder(holder: SoundHolder, position: Int) {
        }
    }
}