package org.javaapp.beatbox

import android.content.res.AssetManager
import android.util.Log

// 상수
private const val TAG = "BeatBox" // 로그 메시지에 사용할 태그
private const val SOUNDS_FOLDER = "sample_sounds" // 애셋이 저장된 폴더 이름

class BeatBox (private val assets : AssetManager){
    // 애셋의 파일 내역을 찾는 함수
    fun loadSounds() : List<String> {
        try {
            val soundNames = assets.list(SOUNDS_FOLDER)!! // 애셋에 있는 파일들의 내역을 얻는다.
            Log.d(TAG, "Found ${soundNames.size} sounds")
            return soundNames.asList() // 문자열 배열에서 리스트로 형변환 후 반환
        } catch (e : Exception) {
            Log.e(TAG, "Could not list assets", e)
            return emptyList() // 빈 리스트 반환
        }
    }
}