package practice.algorithm

fun main() {
    val myArray = arrayOf(arrayOf("birst",12) , arrayOf("aecond", 13), arrayOf("ahird",11), arrayOf("bourth", 14))

    //다중 배열 출력을 위한 contentDeepToString
    println("myArray - ${myArray.contentDeepToString()}")

    //TODO 이게 배열 정렬방식...
//    val result = myArray.sortedWith(compareBy({it[0]}, {it[1]}))
    val result = myArray.sortedWith(compareBy({it[0]}, {it[1]}))
//    println("myArrayResult - ${result.toString()}")

    for (i in result) {
        println(i.contentDeepToString())
    }

    println("result - ${result.javaClass}")

    result.forEach {
        println(it.contentDeepToString())
    }

//    myArray.sortedBy {
//
//    }

//    val myList = listOf(arrayOf("a",1), arrayOf("b",4), arrayOf("c", 3))

    val myList = listOf<TempObject>(
        TempObject("a",1), TempObject("a",4),
            TempObject("d", 3), TempObject("e", 0), TempObject("e",1)
    )

    val resultList = myList.sortedBy { myObject ->
//        ComparablePair(myObject.name, myObject.number)
        //[TempObject(name=e, number=0), TempObject(name=a, number=1), TempObject(name=e, number=1), TempObject(name=d, number=3), TempObject(name=a, number=4)]
        MyTempCompare(2, "b").compareTo(myObject)
    }

//    val resultList = myList.sortedWith(MyTempCompare(2, "b"))

    println("resultList - $resultList")

    val resultSecondList = myList.sortedWith(MyTempCompareSecond())

    println("resultSecondList - $resultSecondList")

//    myList.sortedBy {
//        println("$it")
//        ComparablePair(compareBy({it[0]}), compareBy({it[1]}))
//        println("my - $myArray")
//    }

//    myList.sortedBy {
//        ComparablePair()
//    }

    val tempList = listOf<Int>(1,2,3)
//    tempList.sortedBy {

//    }




}

data class TempObject(val name:String, val number:Int)

data class ComparablePair<A: Comparable<A>, B: Comparable<B>>(
        val first: A,
        val second: B
) : Comparable<ComparablePair<A, B>> {
    override fun compareTo(other: ComparablePair<A, B>): Int {
        println("check - ${this.first} , ${this.second}, ${other.first}, ${other.second}")
        val firstComp = this.first.compareTo(other.first)
        println("test - $firstComp")
        if (firstComp > 0) { return firstComp }
//        println("second - $second")
        return this.second.compareTo(other.second)
    }
}

class MyTempCompare(val number: Int, val text:String) : Comparable<TempObject> {
    override fun compareTo(other: TempObject): Int {
        if (number == other.number) {
            if (text == other.name) {
                return 0
            }
            return other.name.compareTo(text)
        }

        return other.number - number
    }
}

class MyTempCompareSecond() : Comparator<TempObject> {
    override fun compare(o1: TempObject, o2: TempObject): Int {
        if (o1.number == o2.number) {
            return o1.name.compareTo(o2.name)
        }

        return o1.number - o2.number
    }
}