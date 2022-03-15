package kotlinflow.kotlinsample.section02

import kotlinx.coroutines.*

fun main() {
    runBlocking {
        doOneTwoThree()
        println("runBlocking: ${Thread.currentThread().name}")
        println("5!")
    }
}

//예제 10: suspend 함수에서 코루틴 빌더 호출 - launch가 안된다.
//suspend fun doOneTwoThree() {
//    launch {
//        println("launch1: ${Thread.currentThread().name}")
//        delay(1000L)
//        println("3!")
//    }
//
//    launch {
//        println("launch2: ${Thread.currentThread().name}")
//        println("1!")
//    }
//
//    launch {
//        println("launch3: ${Thread.currentThread().name}")
//        delay(500L)
//        println("2!")
//    }
//    println("4!")
//}


//예제 11: 코루틴 스코프 -coroutineScope를 새로 사용하므로써 launch를 가능하게 한다.
//suspend fun doOneTwoThree() = coroutineScope {
//    launch {
//        println("launch1: ${Thread.currentThread().name}")
//        delay(1000L)
//        println("3!")
//    }
//
//    launch {
//        println("launch2: ${Thread.currentThread().name}")
//        println("1!")
//    }
//
//    launch {
//        println("launch3: ${Thread.currentThread().name}")
//        delay(500L)
//        println("2!")
//    }
//    println("4!")
//}

//예제 12: Job을 이용한 제어 - 코루틴 빌더 launch는 Job객체를 반환하며 이를 통해 종료될 때까지 기다릴 수 있습니다.
suspend fun doOneTwoThree() = coroutineScope {
    val job = launch {
        println("launch1: ${Thread.currentThread().name}")
        delay(1000L)
        println("3!")
    }
    job.join()

    launch {
        println("launch2: ${Thread.currentThread().name}")
        println("1!")
    }

//    launch {
//        println("launch3: ${Thread.currentThread().name}")
//        delay(500L)
//        println("2!")
//    }
    //예제 13: 가벼운 코루틴 - 1000번반복해도가벼움.
    repeat(1000) {
        launch {
            println("launch3: ${Thread.currentThread().name}")
            delay(500L)
            println("2!")
        }
    }

    println("4!")
}