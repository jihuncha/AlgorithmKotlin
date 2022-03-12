package study.section01.homework

import java.util.*

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

    println("========================================")
    val test = BitSet(1)
    println(test.size())

    val test2 = BitSet()
    // 이게 64비트네..? 2에 6승이니까. 근데 숫자는 한개만생성하네??
    println(1 shl 6)
}

//default value - 64 bit으로 설정
class BitSetMine() {
    companion object {
        const val BIT_PER_WORD = 1 shl 6
    }

    var nBits = 1 shl 6
    var words: Array<Long> = initWords(BIT_PER_WORD)

    constructor(bitNumber: Int) : this() {
        this.nBits = bitNumber
//        this.words = Array<Long>(nBits){0}
        initWords(bitNumber)
    }

    fun initWords(bitNumber: Int): Array<Long> {
        println("test - ${(bitNumber - 1) shr 6}")
        return Array<Long>(((bitNumber - 1) shr 6) + 1) { 0 }
    }

    override fun toString(): String {
        return words.contentDeepToString()
    }

    fun size() : Int {
        return words.size * BIT_PER_WORD
    }
}