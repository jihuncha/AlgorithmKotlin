package kotlinflow.kotlinsample.section01

import kotlinx.coroutines.*

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
//        launch {
//            println("launch: ${Thread.currentThread().name}")
//            println("World!")
//        }
//        println("runBlocking: ${Thread.currentThread().name}")
//        예제 5: delay 함수
//        delay(500L)
        //예제 6: 코루틴 내에서 sleep - NOT Work!!
//        Thread.sleep(500)
//        println("Hello")

////        예제 7: 한번에 여러 launch - 딜레이 값을 바꿔 보면 suspend된 이후 깨어나는 순서에 따라 출력 결과가 달라집니다.
//        launch {
//            println("launch1: ${Thread.currentThread().name}")
//            delay(1000L)
//            println("3!")
//        }
//        launch {
//            println("launch2: ${Thread.currentThread().name}")
//            println("1!")
//        }
//        println("runBlocking: ${Thread.currentThread().name}")
//        delay(500L)
//        println("2!")
//    }
////    예제 8: 상위 코루틴은 하위 코루틴을 끝까지 책임진다.
//    print("4!")

        launch {
            doThree()
        }
        launch {
            doOne()
        }
        doTwo()
    }

}

//예제 9: suspend 함수
suspend fun doThree() {
    println("doThree/launch: ${Thread.currentThread().name}")
    delay(1000L)
    println("3!")
}

suspend fun doOne() {
    println("doOne/launch: ${Thread.currentThread().name}")
    println("1!")
}

suspend fun doTwo() {
    println("runBlocking: ${Thread.currentThread().name}")
    delay(500L)
    println("2!")
}