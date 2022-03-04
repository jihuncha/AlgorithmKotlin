package practice.kotlinsample

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

fun main() {
    println("main start")
    val scope = GlobalScope
    scope.launch {
        makeFlow().collect { value ->
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