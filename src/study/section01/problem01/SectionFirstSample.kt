package study.section01

import study.section01.utils.Utils
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
import kotlin.time.ExperimentalTime
import kotlin.time.measureTimedValue

@OptIn(ExperimentalTime::class)
fun main() {
    //시간측정을위함.
    val measuredFunTime = measureTimedValue {
        //TODO input파일 받아와서 처리하는부분..
//        val tempInput = arrayListOf<Int>(7, 5, 9, 0, 3, 1, 6, 2, 9, 1, 4, 8, 0, 5, 2)
        val tempInput = Utils().makeRandomList(null)

        //초기화방식 잘알아두기..
        val myList = IntArray(tempInput.size + 1) { 0 }

//        println(myList.toList())

        for (i in tempInput.indices) {
            //TODO 테스트를위해 해당 기능 주석처리
//        if (myList[tempInput[i]] >= 1) {
//            throw java.lang.RuntimeException("Fatal Error!")
//        }

            myList[tempInput[i]] += 1
        }

        writeFile(myList.toList())
    }

    println("time - ${measuredFunTime.duration}")

}

//fun makeRandomList(): List<Int> {
//    //천만까지 만들고 shuffled 적용
//    val resultList = Array<Int>(10000000) { i -> i }.toList().shuffled()
//
//    println(resultList)
//
//    return resultList
//
//    //    val range = (0..10000000)
////    while (resultSet.size < 10000000) {
////        val myNumber = range.random()
////        println(myNumber)
////        resultSet.add(myNumber)
////    }
////
////    return resultSet.toList()
//
////    return arrayListOf<Int>(2, 3, 4, 5)
//}

fun writeFile(result: List<Int>) {
    val path = "/Users/chajihun/Workspace/git_all/AlgorithmKotlin/file"
    val dir = File(path)

    if (!dir.exists()) {
        dir.mkdir()
    }

    val writer = FileWriter(path + "/" + "tempText.txt")
    val buffer = BufferedWriter(writer)

//    var stringResult = ""
//    for (i in result.indices) {
//        //숫자가 단 한번이라도 존재하는 경우
//        if (result[i] >= 1) {
//            stringResult += "$i "
//        }
//    }

    var stringResult = result.toString()

    buffer.write(stringResult)
    buffer.close()
}
