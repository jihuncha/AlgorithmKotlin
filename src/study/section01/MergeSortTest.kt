package study.section01

fun main() {
    val numbers = mutableListOf(38,27,43,3,9,82,10)
    val sortedList = divideAndConquer(numbers)

    println("result - $sortedList")
}

fun divideAndConquer(inputList : List<Int>) : List<Int> {
    //예외처리
    if (inputList.size <= 1) {
        return inputList
    }

    val middle = inputList.size / 2
    println("middle - $middle")

    val leftList = inputList.subList(0, middle)
    val rightList = inputList.subList(middle, inputList.size)

    return merge(divideAndConquer(leftList), divideAndConquer(rightList))
}

fun merge(leftList : List<Int>, rightList : List<Int>) : List<Int> {

    println("left - $leftList , right - $rightList")

    var leftIndex = 0
    var rightIndex = 0
    var resultList : MutableList<Int> = mutableListOf()

    while (leftIndex < leftList.size && rightIndex < rightList.size) {
        if (leftList[leftIndex] <= rightList[rightIndex]) {
            resultList.add(leftList[leftIndex])
            leftIndex += 1
        } else {
            resultList.add(rightList[rightIndex])
            rightIndex += 1
        }
    }

    while (leftIndex < leftList.size) {
        resultList.add(leftList[leftIndex])
        leftIndex += 1
    }

    while (rightIndex < rightList.size) {
        resultList.add(rightList[rightIndex])
        rightIndex += 1
    }

    return resultList
}