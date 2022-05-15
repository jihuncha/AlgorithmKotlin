package algorithm.greedy

import java.util.*


fun main(args: Array<String>) {
    val input = readln()

    var sum = 0

    val charArr: CharArray = input.toCharArray()
    Arrays.sort(charArr) // 오름차순 정렬
    val len = charArr.size

    val sb = StringBuilder()
    // 오름차순 정렬이므로, 맨 끝 원소부터 반대로 탐색
    for (i in len - 1 downTo 0) {
        val num = charArr[i] - '0'
        sum += num
        sb.append(num)
    }

    // 30의 배수인지 판단하기 위한 조건 생성
    if (charArr[0] != '0' || sum % 3 != 0) {    // 만약 3의 배수가 아니라면
        println(-1)
        return
    }

    println(sb.toString())
}

//second Solution
fun checkNumberThird (myNumber: String) : String {
    //입력값에 0이 없는 경우는 -1 처리
    if (!myNumber.contains('0')) return "-1"

    //String to MutableList
    val charsList: MutableList<Char> = myNumber.toMutableList()

    charsList.sortDescending()

    var sum = 0
    var tempString = ""
    for (i in charsList.indices) {
        sum += Integer.parseInt(charsList[i].toString())
        tempString += charsList[i]
    }

    if (sum % 3 == 0 && tempString[tempString.length].equals("0")) return tempString
    return "-1"

//    //0을 하나 제거한다. - 맨뒤에 있는 걸로 가정.
//    charsList.remove('0')

//    val resultList = mutableListOf<Int>()

//    for (i in charsList.indices) {
////        println(charsList[i])
//        resultList.add(Integer.parseInt(charsList[i].toString()))
//    }
//
//    if (resultList.sum() % 3 != 0) return "-1"
//
//    resultList.sortDescending()
//
//    var temp = ""
//    for (i in resultList) {
//        temp += i.toString()
//    }
//    temp += "0"
//
//    return temp
}