package kotlinflow.kotlinsample.section04

import kotlinx.coroutines.*
import kotlin.random.Random
import kotlin.system.measureTimeMillis


suspend fun getRandom1(): Int {
    delay(1000L)
    return Random.nextInt(0, 500)
}

suspend fun getRandom2(): Int {
    delay(1000L)
    return Random.nextInt(0, 500)
}

fun main() = runBlocking {
//    예제 23: suspend 함수들의 순차적인 수행 - 순차적으로 실행되서 2000Ms소요됨.
    val elapsedTime = measureTimeMillis {
//        val value1 = getRandom1()
//        val value2 = getRandom2()

//        예제 24: async를 이용해 동시 수행하기 - async 키워드를 통해서 동시 수행되므로 1000ms 소요됨
//        val value1 = async { getRandom1() }
//        val value2 = async { getRandom2() }

        //예제 25: aync 게으르게 사용하기
        val value1 = async(start = CoroutineStart.LAZY) { getRandom1() }
        val value2 = async(start = CoroutineStart.LAZY) { getRandom2() }

        value1.start()
        value2.start()

        println("${value1.await()} + ${value2.await()} = ${value1.await() + value2.await()}")
    }
    println(elapsedTime)

    doSecondMain()
}

//예제 26: async를 사용한 구조적인 동시성
fun doSecondMain() = runBlocking {
    println("================================================================")
    try {
        doSomething()
    } catch (e: IllegalStateException) {
        println("doSomething failed: $e")
    }
}

suspend fun getRandom1Two(): Int {
    try {
        delay(1000L)
        return Random.nextInt(0, 500)
    } finally {
        println("getRandom1Two is cancelled.")
    }
}

suspend fun getRandom2Two(): Int {
    delay(500L)
    throw IllegalStateException()
}

suspend fun doSomething() = coroutineScope {
    val value1 = async { getRandom1Two() }
    val value2 = async { getRandom2Two() }
    try {
        println("${value1.await()} + ${value2.await()} = ${value1.await() + value2.await()}")
    } finally {
        println("doSomething is cancelled.")
    }
}

