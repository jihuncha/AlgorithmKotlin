package kotlinflow.kotlinsample.section03

import kotlinx.coroutines.*

fun main() {
    runBlocking {
//        doOneTwoThreeSecond()
//        println("runBlocking: ${Thread.currentThread().name}")
//        println("5!")

        //예제 21: 타임 아웃 - TimeoutCancellationException 발생
//        withTimeout(500L) {
//            doCountSecond()
//        }

        //예제 22: withTimeoutOrNull
        val result = withTimeoutOrNull(500L) {
            doCountSecond()
            true
        } ?: false
        println(result)
    }
}

//예제 19: finally를 같이 사용
//suspend fun doOneTwoThreeSecond() = coroutineScope {
//    val job1 = launch {
//        try {
//            println("launch1: ${Thread.currentThread().name}")
//            delay(1000L)
//            println("3!")
//        } finally {
//            println("job1 is finishing!")
//        }
//    }
//
//    val job2 = launch {
//        try {
//            println("launch2: ${Thread.currentThread().name}")
//            delay(1000L)
//            println("1!")
//        } finally {
//            println("job2 is finishing!")
//        }
//    }
//
//    val job3 = launch {
//        try {
//            println("launch3: ${Thread.currentThread().name}")
//            delay(1000L)
//            println("2!")
//        } finally {
//            println("job3 is finishing!")
//        }
//    }
//
//    delay(800L)
//    job1.cancel()
//    job2.cancel()
//    job3.cancel()
//    println("4!")
//}

//예제 20: 취소 불가능한 블록
suspend fun doOneTwoThreeSecond() = coroutineScope {
    val job1 = launch {
        withContext(NonCancellable) {
            println("launch1: ${Thread.currentThread().name}")
            delay(1000L)
            println("3!")
        }
        delay(1000L)
        print("job1: end")
    }

    val job2 = launch {
        withContext(NonCancellable) {
            println("launch1: ${Thread.currentThread().name}")
            delay(1000L)
            println("1!")
        }
        delay(1000L)
        print("job2: end")
    }

    val job3 = launch {
        withContext(NonCancellable) {
            println("launch1: ${Thread.currentThread().name}")
            delay(1000L)
            println("2!")
        }
        delay(1000L)
        print("job3: end")
    }

    delay(800L)
    job1.cancel()
    job2.cancel()
    job3.cancel()
    println("4!")
}

//예제 21: 타임 아웃 - main의 withTimeOut 로직 참고
suspend fun doCountSecond() = coroutineScope {
    val job1 = launch(Dispatchers.Default) {
        var i = 1
        var nextTime = System.currentTimeMillis() + 100L

        while (i <= 10 && isActive) {
            val currentTime = System.currentTimeMillis()
            if (currentTime >= nextTime) {
                println(i)
                nextTime = currentTime + 100L
                i++
            }
        }
    }
}
