package kotlinflow.kotlinsample.section03

import kotlinx.coroutines.*

fun main() = runBlocking {
//    doOneTwoThree()
//    println("runBlocking: ${Thread.currentThread().name}")
//    println("5!")

    doCount()
}

suspend fun doOneTwoThree() = coroutineScope {
    val job1 = launch {
        println("launch1: ${Thread.currentThread().name}")
        delay(1000L)
        println("3!")
    }

    val job2 = launch {
        println("launch2: ${Thread.currentThread().name}")
        println("1!")
    }

    val job3 = launch {
        println("launch3: ${Thread.currentThread().name}")
        delay(500L)
        println("2!")
    }

    //예제 14: Job에 대해 취소 - delay 시간보다 긴 3은 출력이 안되는 모습을 볼 수 있다.
    delay(800L)
    job1.cancel()
    job2.cancel()
    job3.cancel()
    println("4!")
}

//예제 15: 취소 불가능한 Job
//suspend fun doCount() = coroutineScope {
//    val job1 = launch(Dispatchers.Default) {
//        var i = 1
//        var nextTime = System.currentTimeMillis() + 100L
//
//        while (i <= 10) {
//            val currentTime = System.currentTimeMillis()
//            if (currentTime >= nextTime) {
//                println(i)
//                nextTime = currentTime + 100L
//                i++
//            }
//        }
//    }
//
//    delay(200L)
//    job1.cancel()
//    println("doCount Done!")
//}

//cancel과 join
suspend fun doCount() = coroutineScope {
    val job1 = launch(Dispatchers.Default) {
        var i = 1
        var nextTime = System.currentTimeMillis() + 100L

        while (i <= 10) {
            val currentTime = System.currentTimeMillis()
            if (currentTime >= nextTime) {
                println(i)
                nextTime = currentTime + 100L
                i++
            }
        }
    }

    delay(200L)
//    job1.cancel()
//    job1.join()
    //예제18.cancelAndJoin
    job1.cancelAndJoin()
    println("doCount Done!")
}