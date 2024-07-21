package org.javaapp.beatbox

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable

// 뷰모델 : 뷰로 보여줄 데이터를 형식화하는 역할 담당
class SoundViewModel(private val beatBox: BeatBox) : BaseObservable() {
    fun onButtonClicked() {
        sound?.let{
            beatBox.play(it)
        }
    }
    // BaseObservable 상속
    // 사용할 Sound 객체
    var sound : Sound? = null
        set(sound) {
            field = sound
            notifyChange() // 속성의 값이 변경될 때마다 호출
        }

    // 버튼에 보여줄 제목
    @get:Bindable // 바인딩되는 속성에 애노테이션 지정
    val title : String?
        get() = sound?.name
}