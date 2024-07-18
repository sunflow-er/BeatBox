package org.javaapp.beatbox

class SoundViewModel {
    // 사용할 Sound 객체
    var sound : Sound? = null
        set(sound) {
            field = sound
        }

    // 버튼에 보여줄 제목
    val title : String?
        get() = sound?.name
}