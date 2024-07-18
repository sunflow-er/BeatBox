package org.javaapp.beatbox

private const val WAV = ".wav"

class Sound(val assetPath : String) {
    // 화면에 보여줄 음원 파일의 이름
    val name= assetPath.split("/").last().removeSuffix(WAV) // 경로 문자열의 맨 끝에 있는 음원 파일 이름에서 확장자인 .wav를 제거한다.
}