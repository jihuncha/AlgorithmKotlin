package study.section01.homework

import java.util.*

fun main() {
    //결국초기화단계에서 6만큼이동 즉 1바이트만큼 (long형이기떄문)
    val tempCheck = 1 shl 6
//    println(tempCheck)

    val temp = BitSetMine()
//    println(temp.words.size)
//    println(temp.toString())

    val tempSecond = BitSetMine(2)
//    println(tempSecond.words.size)
//    println(tempSecond.toString())
    println(tempSecond.wordIndex(68))

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
        //1바이트가 Long타입이므로 그 만큼 늘려줘야하기 떄문
        const val BIT_PER_WORD = 1 shl 6

        //Long 타입은 64비트 -> 2가 6자리 이므로 6자리 정의
        const val ADDRESS_BITS_PER_WORD = 6
    }

    //비트값
    var nBits = 1 shl 6

    //글자수값 (Long타입) - 초기의 경우 그냥 64로 정의한다 (Long)
    var words: Array<Long> = initWords(BIT_PER_WORD)

    //내가 몇개의 Long값을 쓰고 있는지 관리한다.
    var wordInUse = 0

    constructor(bitNumber: Int) : this() {
        this.nBits = bitNumber
        initWords(bitNumber)
    }

    /**
     * 실제 Long 배열의 index를 반환하는듯 (?)
     * */
    fun wordIndex(bitIndex: Int): Int {
        println("wordIndex - $bitIndex - ${bitIndex shr ADDRESS_BITS_PER_WORD} ")
        return bitIndex shr ADDRESS_BITS_PER_WORD
    }

    /**
     * 자리수를 반환해주는듯?
     * ex) 65 를 넣으면 1자리.. 129 두자리
     * */
    private fun initWords(bitNumber: Int): Array<Long> {
        return Array<Long>(wordIndex(bitNumber - 1) + 1) { 0 }
//        return Array<Long>(((bitNumber - 1) shr 6) + 1) { 0 }
    }

    override fun toString(): String {
        return words.contentDeepToString()
    }

    /**
     * size 반환함수
     * 실제 글자길이 * Long의 비트수를 곱하여 실제 비트 사이즈를 반환한다.
     * */
    fun size(): Int {
        return words.size * BIT_PER_WORD
    }

    /**
     * 해당하는 비트에 값을 set해준다
     * */
    fun set(bitIndex : Int) {
        //일단 해당하는 글자의 index 가져온다

        val wordIndex = wordIndex(bitIndex)


    }
}