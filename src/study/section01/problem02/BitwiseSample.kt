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

    println("==========================================================")

    //bitwise operation - and, or
    val number1 = 12
    val number2 = 25
    val result: Int

    //    12 = 00001100 (In Binary)
//    25 = 00011001 (In Binary)
    //2진법으로 표현, padStart로 빈자리 채우기
    println(number1.toString(2).padStart(8,'0'))
    println(number2.toString(2).padStart(8,'0'))

//    result = number1 or number2
//    println(result) //29

    result = number1 and number2
    println(result) //8

    //shift
    //shl - shift left
    //shr - shift right
    val number3 = 212
    println(number3.toString(2).padStart(16,'0')) //212
    println(number3.shl(1).toString(2).padStart(16,'0')) // 424
    println(number3.shr(1).toString(2).padStart(16,'0')) // 106
}