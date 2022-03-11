package study.section01.homework

import java.util.Arrays

//class BitSetMine(range:Int) {
//    constructor()
//}

fun main() {
    //결국초기화단계에서 6만큼이동 즉 1바이트만큼 (long형이기떄문)
    val tempCheck = 1 shl 6
    println(tempCheck)

    val temp = BitSetMine()
    println(temp.words.size)
    println(temp.toString())

    val tempSecond = BitSetMine(2)
    println(tempSecond.words.size)
    println(tempSecond.toString())
}

//default value - 64 bit으로 설정
class BitSetMine() {
    var nBits = 1 shl 6
    var words : Array<Long> = Array<Long>(1 shl 6) {0}

    constructor(bitNumber: Int) : this() {
        this.nBits = bitNumber
//        this.words = Array<Long>(nBits){0}
        initWords(bitNumber)
    }

    fun initWords(bitNumber : Int) {
        words = Array<Long>(((bitNumber-1) shr 6) + 1) {0}
    }

    override fun toString(): String {
        return words.contentDeepToString()
    }
}