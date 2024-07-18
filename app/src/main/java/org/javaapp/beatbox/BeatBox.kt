package org.javaapp.beatbox

import android.content.res.AssetManager
import android.util.Log

// 상수
private const val TAG = "BeatBox" // 로그 메시지에 사용할 태그
private const val SOUNDS_FOLDER = "sample_sounds" // 애셋이 저장된 폴더 이름

class BeatBox (private val assets : AssetManager){

    val sounds : List<Sound>

    init {
        sounds = loadSounds()
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
            sounds.add(sound)
        }
        return sounds 
    }
}