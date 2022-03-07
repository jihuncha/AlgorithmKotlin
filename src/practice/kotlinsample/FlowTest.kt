package practice.kotlinsample

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.collect

fun main() = runBlocking<Unit> {
    println("main start")
    val scope = GlobalScope
    scope.launch {
        // https://stackoverflow.com/questions/65559153/is-kotlin-flows-collect-is-only-internal-kotlinx-coroutines-api
        makeFlow().buffer().collect { value ->
            println("got $value")
        }
        println("flow is completed")
    }
}

fun makeFlow() = flow {
    println("sending first value")
    emit(1)
    println("first value collected, sending another value")
    emit(2)
    println("second value collected, sending a third value")
    emit(3)
    println("done")
}