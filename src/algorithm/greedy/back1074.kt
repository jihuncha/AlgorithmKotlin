package algorithm.greedy

import kotlin.math.pow


// https://www.acmicpc.net/problem/1074

// Z

//시간 제한 메모리 제한 제출 정답 맞힌 사람 정답 비율
//0.5 초 (추가 시간 없음) 512 MB 46882 17053 12811 38.230%
//문제
//한수는 크기가 2N × 2N인 2차원 배열을 Z모양으로 탐색하려고 한다. 예를 들어, 2×2배열을 왼쪽 위칸, 오른쪽 위칸, 왼쪽 아래칸, 오른쪽 아래칸 순서대로 방문하면 Z모양이다.
//
//
//
//N > 1인 경우, 배열을 크기가 2N-1 × 2N-1로 4등분 한 후에 재귀적으로 순서대로 방문한다.
//
//다음 예는 22 × 22 크기의 배열을 방문한 순서이다.
//
//
//
//N이 주어졌을 때, r행 c열을 몇 번째로 방문하는지 출력하는 프로그램을 작성하시오.
//
//다음은 N=3일 때의 예이다.
//
//
//
//입력
//첫째 줄에 정수 N, r, c가 주어진다.
//
//출력
//r행 c열을 몇 번째로 방문했는지 출력한다.

// 2 3 1

// 11

// 3 7 7

// 63

// 1 0 0

// 0

//4 7 7

// 63

//10 511 511

//262143

//10 512 512

//786432

var count = 0

fun main() {
    val input = readln()

    val inputList = input.split(' ')

    //한변의 길이 -> Size
    val size = 2.0.pow(inputList[0].toDouble()).toInt()

    findResult(size, inputList[1].toInt(), inputList[2].toInt())

    println(count)
}

fun findResult(n:Int, r:Int, c:Int) : Int {
//    println("n,r,c,result - $n, $r, $c, $count")

    if (n == 1) {
        return count
    }

    //왼쪽위
    if (r < n/2 && c < n/2) {
        findResult(n/2, r, c)
    }
    //오른쪽위 -> 1사분면의 영역만큼을 더한다 (n * n / 4)
    //열의 위치를 변경해준다 (size반 만큼축소)
    else if (n/2 in (r + 1)..c) {
        count += ((n * n) / 4)
        findResult(n/2, r, c - n/2)
    }
    //왼쪽아래 -> 1,2 사분면을 더한다.
    //행의 위치를 상대 위치로 변경
    else if (n/2 in (c + 1)..r) {
        count += ((n * n) / 4) * 2
        findResult(n/2, r - n/2, c)
    }
    //오른쪽아래 -> 1,2,3 사분면을 더한다.
    //행과 열의 위치를 변경
    else {
        count += ((n * n) / 4) * 3
        findResult(n/2, r - n/2, c - n/2)
    }

    return count
}