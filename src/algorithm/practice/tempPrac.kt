package algorithm.practice

import java.util.Collections

fun main() {
    val myList = mutableListOf<Int>(1,2,3,4)
    print(myList)

    Collections.swap(myList, 0,1)

    print(myList)

}