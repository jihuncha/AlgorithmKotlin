package study.section01.problem02

import study.section01.utils.Utils
import java.util.*

fun main() {
    val bitSet = BitSet()

    println(bitSet)

    //100개
    val myTempList = Utils().makeRandomList(100)

    println(myTempList)

    for (i in myTempList) {
        bitSet.set(i)
    }

    //100개 선언했으므로 true
    println(bitSet[1])
    //101번쨰는 false
    println(bitSet[101])

    //size를 보면 128bit -> 16byte
    println(bitSet.size())  //128

    //128 씩 늘어나는것을 볼 수 있다.
    bitSet.set(129)
    println(bitSet.size())  //256

    //bitwise operation
    val number1 = 12
    val number2 = 25
    val result: Int

//    result = number1 or number2
//    println(result) //29

    result = number1 and number2
    println(result) //8
}