package algorithm.greedy

import java.util.Collections

// https://www.acmicpc.net/problem/10610

/*문제
어느 날, 미르코는 우연히 길거리에서 양수 N을 보았다. 미르코는 30이란 수를 존경하기 때문에, 그는 길거리에서 찾은 수에 포함된 숫자들을 섞어 30의 배수가 되는 가장 큰 수를 만들고 싶어한다.

미르코를 도와 그가 만들고 싶어하는 수를 계산하는 프로그램을 작성하라.

입력
N을 입력받는다. N는 최대 105개의 숫자로 구성되어 있으며, 0으로 시작하지 않는다.

출력
미르코가 만들고 싶어하는 수가 존재한다면 그 수를 출력하라. 그 수가 존재하지 않는다면, -1을 출력하라.*/

//30
//30

//102
//210

//2931
//-1

//80875542
//88755420

//thinking
//1. 일단 0이없다 -> 30의배수가 될수 없음 -> 제외
//2. 일단 0이 맨앞에 될수없고, 0이 맨뒤에 하나는 와야한다는 전제조건이 깔려야함
//3. 그 경우로 나눈 문자를 순열(?)로 표현하여 가장 큰값을 가져온다?
//위 조건의 만족하는 숫자의 경우의수를 전부 탐색해야할듯??
//한개라도 있을 경우 더 줄일 수 있지 않을까?

///timeout......


fun main(args: Array<String>) {
    val input = readln();

    print(checkNumber(input))
}

fun checkNumber (myNumber: String) : Int {
    //입력값에 0이 없는 경우는 -1 처리
    if (!myNumber.contains('0')) return -1

    //String to MutableList
    val charsList: MutableList<Char> = myNumber.toMutableList()

    //0을 하나 제거한다. - 맨뒤에 있는 걸로 가정.
    charsList.remove('0')

    //큰수대로 sorting
    charsList.sortDescending()

    //TODO 순열 - 코틀린 순열 library없는거 충격적이네..
//    for (i in 0..charsList.lastIndex - 1) {
//        var temp = charsList.toList().toMutableList()
////        println(temp)
//        for (j in i + 2..charsList.lastIndex) {
//            temp.let { gow ->
//                Collections.swap(gow, j-1, j)
//                println("result - $gow")
//            }
////            Collections.swap(temp, j, j)
//            println(temp)
//        }
//
//        Collections.swap(charsList, i, i+1)
//        println("gogogogogo - $charsList")
//    }

    val permuteList = allPermutationsSP(charsList)

    for (i in permuteList.indices) {
        var result = permuteList[i].joinToString("").toInt()
        if (result % 3 == 0) {
            return result * 10
        }
    }

    return -1;
}

fun <T : Comparable<T>> allPermutationsSP(list: List<T>): List<List<T>> {
    //초기List값 대입
    val mutableList = mutableListOf<T>().apply {
        addAll(list)
    }
    val allPermutations = mutableListOf<List<T>>()

    // swap을 이용한 permutation은 순서를 보장하지 않음
    // 이 방식은 Back tracking 기법을 사용함
    fun swapPermutationRecursive(mutableList: MutableList<T>, depth: Int) {
        // idx1의 원소와 idx2의 원소의 위치를 바꿈
        fun swap(idx1: Int, idx2: Int) {
            val temp = mutableList[idx1]
            mutableList[idx1] = mutableList[idx2]
            mutableList[idx2] = temp
        }
        if (depth == mutableList.size) {
            allPermutations.add(mutableListOf<T>().apply { addAll(mutableList) })
        }
        for (i in depth..mutableList.lastIndex) {
            swap(depth, i)
            swapPermutationRecursive(mutableList, depth + 1)
            swap(depth, i)
        }
    }
    swapPermutationRecursive(mutableList, 0)
    return allPermutations
}