package org.javaapp.beatbox

import org.hamcrest.MatcherAssert
import org.hamcrest.core.Is.`is`
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test
import javax.security.auth.Subject

class SoundViewModelTest {

    private lateinit var sound : Sound
    private lateinit var subject: SoundViewModel

    @Before
    fun setUp() {
        // 테스트할 클래스의 인스턴스와 이 인스턴스가 필요로 하는 객체 생성
        sound = Sound("assetPath")
        subject = SoundViewModel() // 테스트의 대상이 되는 객첸
        subject.sound = sound
    }
    
    // SoundViewModel의 title 속성값이 Sound의 name 속성값과 일치하는지 검사하는 테스트 함수
    @Test
    fun exposesSoundNameAsTitle() {
        MatcherAssert.assertThat(subject.title, `is`(sound.name)) // 테스트 대상의 title 속성값이 Sound의 name 속성값과 같아야 함을 나타냄, 두 속성값이 다르면 테스트는 실패
    }
    
}