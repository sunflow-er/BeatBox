package org.javaapp.beatbox

import android.content.res.AssetFileDescriptor
import android.content.res.AssetManager
import android.media.SoundPool
import android.util.Log
import java.io.IOException

// 상수
private const val TAG = "BeatBox" // 로그 메시지에 사용할 태그
private const val SOUNDS_FOLDER = "sample_sounds" // 애셋이 저장된 폴더 이름
private const val MAX_SOUNDS = 5 // 동시에 재생될 수 있는 최대 음악 개수, 여섯 번째 음원을 재생하려고 하면 가장 오래된 음원의 재생이 중단된다.

class BeatBox (private val assets : AssetManager){

    val sounds : List<Sound>
    private val soundPool = SoundPool.Builder().setMaxStreams(MAX_SOUNDS).build() // SoundPool 객체 생성

    init {
        sounds = loadSounds()
    }

    
    // 음원 재생
    fun play(sound: Sound) {
        sound.soundId?.let {
            soundPool.play(it, 1.0f, 1.0f, 1, 0, 1.0f) // (음원ID, 왼쪽 볼륨, 오른쪽 볼륨, 스트림 우선순위, 반복 재생 여부, 재생 속도)
        }
    }

    // asset 파일에 저장된 음원 정보로 Sound 인스턴스 리스트를 만들어 반환
    private fun loadSounds() : List<Sound> {

        val soundNames : Array<String>

        // 애셋 음원 파일의 이름을 리스트로 가져오기
        try {
            soundNames = assets.list(SOUNDS_FOLDER)!! // 애셋에 있는 파일들의 내역을 얻는다.
            // Log.d(TAG, "Found ${soundNames.size} sounds")
        } catch (e : Exception) {
            Log.e(TAG, "Could not list assets", e)
            return emptyList() // 빈 리스트 반환
        }

        // 이름 리스트로 Sound 인스턴스 리스트를 생성하고 반환
        val sounds = mutableListOf<Sound>()
        soundNames.forEach { filename -> // 문자열 정보를 Sound 객체로
            val assetPath = "$SOUNDS_FOLDER/$filename"
            val sound = Sound(assetPath)
            try {
                load(sound) // 모든 음원을 로드
                sounds.add(sound)
            } catch (ioe : IOException) {
                Log.e(TAG, "Could not load sound $filename", ioe)
            }
        }
        return sounds 
    }

    // SoundPool에 Sound 인스턴스를 로드하기 위한 함수
    private fun load(sound : Sound) {
        val afd : AssetFileDescriptor = assets.openFd(sound.assetPath)
        val soundId = soundPool.load(afd, 1) // 나중에 재생할 음원 파일을 SoundPool에 로드하고, 정수ID 반환
        sound.soundId = soundId
    }
}