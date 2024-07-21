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

class MainActivity : AppCompatActivity() { // 컨트롤러 : 데이터 바인딩과 뷰모델을 초기화하고 연결하는 역할
    private lateinit var beatBox: BeatBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        beatBox = BeatBox(assets)

        // 바인딩
        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(
                this,
                R.layout.activity_main
            ) // 액티비티의 레이아웃을 설정하고, 바인딩 클래스 인스턴스 생성

        // 리사이클러 뷰
        binding.recyclerView.apply {
            layoutManager = GridLayoutManager(context, 3)
            adapter = SoundAdapter(beatBox.sounds)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        beatBox.release() // 액티비티가 소멸하면, SoundPool 클린업
    }

    private inner class SoundHolder(private val binding: ListItemSoundBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.viewModel = SoundViewModel(beatBox) // 뷰모델 인스턴스 생성
        }

        fun bind(sound: Sound) {
            binding.apply {
                viewModel?.sound = sound // 뷰모델 속성 변경
                executePendingBindings() // 데이터 변경 즉시 반영
            }
        }
    }

    private inner class SoundAdapter(private val sounds: List<Sound>) :
        RecyclerView.Adapter<SoundHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SoundHolder {
            // ListItemSoundBinding 바인딩 클래스의 인스턴스 생성 및 참조
            val binding = DataBindingUtil.inflate<ListItemSoundBinding>(
                layoutInflater,
                R.layout.list_item_sound,
                parent,
                false
            )

            return SoundHolder(binding)
        }

        override fun getItemCount(): Int {
            return sounds.size
        }

        override fun onBindViewHolder(holder: SoundHolder, position: Int) {
            val sound = sounds[position]
            holder.bind(sound)
        }
    }
}