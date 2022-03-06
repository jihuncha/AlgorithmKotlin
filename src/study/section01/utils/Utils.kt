package study.section01.utils

class Utils {
//    fun makeRandomListAll(): List<Int> {
//        //천만까지 만들고 shuffled 적용
//        val resultList = Array<Int>(10000000) { i -> i }.toList().shuffled()
//
//        println(resultList)
//
//        return resultList
//    }

    fun makeRandomList(number:Int?): List<Int> {
        val resultList = Array<Int>(number?: 10000000) { i -> i }.toList().shuffled()

        println("makeRandomList/number - $number , list - $resultList")

        return resultList
    }
}