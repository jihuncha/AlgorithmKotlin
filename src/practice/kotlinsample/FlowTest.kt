package practice.kotlinsample

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.collect

//fun main() = runBlocking<Unit> {
fun main() {
    println("main start")
//    val scope = GlobalScope
    //TODO 왜 글로벌스코프로 안되지?
//    GlobalScope.launch
    runBlocking {
        // https://stackoverflow.com/questions/65559153/is-kotlin-flows-collect-is-only-internal-kotlinx-coroutines-api
//        runBlocking {
        makeFlow().collect { value ->
            println("got $value")
        }
        println("flow is completed")
//        }
    }
    println("End Main")
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