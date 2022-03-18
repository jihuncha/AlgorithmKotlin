package kotlinflow.kotlinsample.section05

import kotlinx.coroutines.*

//예제 27: 코루틴 디스패처
//fun main() = runBlocking<Unit> {
//    launch {
//        println("부모의 콘텍스트 / ${Thread.currentThread().name}")
//    }
//
//    launch(Dispatchers.Default) {
//        println("Default / ${Thread.currentThread().name}")
//    }
//
//    launch(Dispatchers.IO) {
//        println("IO / ${Thread.currentThread().name}")
//    }
//
//    launch(Dispatchers.Unconfined) {
//        println("Unconfined / ${Thread.currentThread().name}")
//    }
//
//    launch(newSingleThreadContext("Fast Campus")) {
//        println("newSingleThreadContext / ${Thread.currentThread().name}")
//    }
//}

//예제 28: async에서 코루틴 디스패처 사용
fun main() = runBlocking<Unit> {
//    async {
//        println("부모의 콘텍스트 / ${Thread.currentThread().name}")
//    }
//
//    async(Dispatchers.Default) {
//        println("Default / ${Thread.currentThread().name}")
//    }
//
//    async(Dispatchers.IO) {
//        println("IO / ${Thread.currentThread().name}")
//    }
//
//    async(Dispatchers.Unconfined) {
//        println("Unconfined / ${Thread.currentThread().name}")
//    }
//
//    async(newSingleThreadContext("Fast Campus")) {
//        println("newSingleThreadContext / ${Thread.currentThread().name}")
//    }

    //예제 29: Confined 디스패처 테스트
//    async(Dispatchers.Unconfined) {
//        println("Unconfined / ${Thread.currentThread().name}")
//        delay(1000L)
//        println("Unconfined / ${Thread.currentThread().name}")
//    }

    //예제 30: 부모가 있는 Job과 없는 Job
    val job = launch {
        launch(Job()) {
            println(coroutineContext[Job])
            println("launch1: ${Thread.currentThread().name}")
            delay(1000L)
            println("3!")
        }

        launch {
            println(coroutineContext[Job])
            println("launch2: ${Thread.currentThread().name}")
            delay(1000L)
            println("1!")
        }
    }

    delay(500L)
    job.cancelAndJoin()
    delay(1000L)
}