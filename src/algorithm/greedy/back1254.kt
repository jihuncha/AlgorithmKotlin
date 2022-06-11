package algorithm.greedy

// https://www.acmicpc.net/problem/1254

// 팰랜드롬 만들기
//
//동호와 규완이는 212호에서 문자열에 대해 공부하고 있다. 규완이는 팰린드롬을 엄청나게 좋아한다. 팰린드롬이란 앞에서부터 읽으나 뒤에서부터 읽으나 같게 읽히는 문자열을 말한다.
//
//동호는 규완이를 위한 깜짝 선물을 준비했다. 동호는 규완이가 적어놓고 간 문자열 S에 0개 이상의 문자를 문자열 뒤에 추가해서 팰린드롬을 만들려고 한다. 동호는 가능하면 가장 짧은 문자열을 만들려고 한다.
//
//동호가 만들 수 있는 가장 짧은 팰린드롬의 길이를 출력하는 프로그램을 작성하시오.

//입력
//첫째 줄에 문자열 S가 주어진다. S는 알파벳 소문자로만 이루어져 있고, 길이는 최대 50이다.
//
//출력
//첫째 줄에 동호가 만들 수 있는 가장 짧은 팰린드롬의 길이를 출력한다.


//abab

//5

//abacaba

//7

//qwerty
//(6)

//11

//abdfhdyrbdbsdfghjkllkjhgfds
// (27)

//38

//aeaeabaeaeawbx

//abcvwerscba
//11글자인데
//abcvwersrewvcba -> 15개면될거같은데
//abcvwerscbabcsrewv -> 21개처리로해야함?????

//0개 이상의 문자를 문자열 뒤에 추가해서 !! 문제잘보기..


//팰랜드롬인 문자인 경우는 그대로출력
//팰런드롬이 아닌 경우 검수를 어떻게해야할까

fun main() {
    val input = readln()
    val reverseInput = input.reversed()

    if (input == reverseInput) {
        println(input.length)
        return
    }

    for (i in input.indices) {
        if (isPalindrome(input.substring(i))) {
            println(input.length + i)
            return
        }
    }

    println(input.length)
}

fun isPalindrome (input: String) : Boolean {
    for (i in 0 .. (input.length) / 2) {
        if (input[i] != input[input.length - 1 - i]) {
            return false
        }
    }
    return true
}

//    val input = readln()
//    val reverseInput = input.reversed()
//
//    //팰랜드롬인 경우
//    if (input == reverseInput) {
//        println(input.length)
//        return
//    }
//
//    var palindromeLength = 0
//
//    for (i in (input.length - 1) downTo  (input.length - 1) / 2) {
//        palindromeLength = Math.max(palindromeLength, checkPalindorome(input, i));
//    }
//
//
////    var backIndex = input.length - 1
////
////    for (i in 0 .. input.length - 1) {
////        if (input[i] == input[backIndex]) {
////            backIndex -= 1
////            palindromeLength += 1
////            println("test $i,  $backIndex , $palindromeLength, ${input[i]}")
////        }
////    }
//
//    println("pa - $palindromeLength")
//
//    println(input.length + (input.length - palindromeLength))

fun checkPalindorome(input:String, lastIndex:Int) : Int {
    var backIndex = lastIndex
    var result = 0
    var connect = false
    var realResult = 0

    for (i in 0 .. lastIndex) {
        if (input[i] == input[backIndex]) {
            connect = true
            backIndex -= 1
            result += 1
        } else {
            if (connect) {
                connect = false
                realResult = Math.max(result, realResult)
                result = 0
            }
        }
    }

    return realResult
}

//포기기ㅣ이이ㅣ잉