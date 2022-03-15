package practice.kotlinsample.section01

import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

fun main() {
//    예제 1: 간단한 코루틴
    runBlocking {
//        예제 2: 코루틴 빌더의 수신 객체
        //println(this) //"coroutine#1":BlockingCoroutine{Active}@7bb11784

//        예제 3: 코루틴 컨텍스트
        //println(coroutineContext) //[CoroutineId(1), "coroutine#1":BlockingCoroutine{Active}@7bb11784, BlockingEventLoop@47fd17e3]
        //println(Thread.currentThread().name)
        //println("Hello")

//        예제 4: launch 코루틴 빌더
        //launch 내용이 나중에 호출됨을 알수 있다 (runblocking 을 사용했기때문 -> )
        launch {
            println("launch: ${Thread.currentThread().name}")
            println("World!")
        }
        println("runBlocking: ${Thread.currentThread().name}")
//        예제 5: delay 함수
        delay(500L)
        println("Hello")
    }
}