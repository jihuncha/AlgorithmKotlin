package study.section01.problem3

import study.section01.utils.Utils
import java.util.*
import kotlin.time.ExperimentalTime
import kotlin.time.measureTimedValue

fun main() {
    //100만개 넘버 생성
    val numberList = Utils().makeRandomList(1000000)

    measureOriginalSort(numberList)

    measureUsingBitset(numberList)
}

@OptIn(ExperimentalTime::class)
fun measureOriginalSort(numberList : List<Int>) {
    val measuredFunTime = measureTimedValue {
        numberList.sorted()
    }

    println("measureOriginalSort - ${measuredFunTime.duration}")
}

@OptIn(ExperimentalTime::class)
fun measureUsingBitset(numberList : List<Int>) {
    val measuredFunTime = measureTimedValue {
        sortUsingBitset(numberList)
    }

    println("measureUsingBitwise - ${measuredFunTime.duration}")
}

fun sortUsingBitset(numberList : List<Int>): List<Int> {
    val bitset = BitSet(10000000)

    for (i in numberList) {
        bitset.set(i)
    }

    //List로 변환
    val myList = mutableListOf<Int>()
    for (j in 0 .. bitset.size()) {
        if (bitset.get(j)) {
            myList.add(j)
        }
    }

    return myList
}