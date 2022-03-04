package study.section01

fun main() {
    val array = mutableListOf<Int>(7, 5, 9, 0, 3, 1, 6, 2, 4, 8)
    val result = insertionSort(array)
    println(result)
}

//얜 무조건 두번씩인데?
fun insertionSort(array: MutableList<Int>): List<Int> {
    //얜 무조건 두번씩인데? - 시간복잡도..
//    for (index in 0..array.size - 1) {
//        for (indexNext in index + 1..array.size - 1) {
//            println("index - $index, indexNext - $indexNext")
//            if (array[index] > array[indexNext]) {
//                val temp = array[indexNext]
//                array[indexNext] = array[index]
//                array[index] = temp
//            }
//        }
//    }

    //뒤에서부터 체크해야한다?? -> 이래야 시간복잡도준다.(이전값들만 비교하면서 비교)
    for (index in 0..array.size - 1) {
        for (indexNext in index downTo 1) {
//            println("index - $index, indexNext - $indexNext")
            if (array[indexNext] < array[indexNext - 1]) {
                val temp = array[indexNext]
                array[indexNext] = array[indexNext - 1]
                array[indexNext - 1] = temp
            }
        }
    }

    return array
}