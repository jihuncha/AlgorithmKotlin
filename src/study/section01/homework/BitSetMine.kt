package study.section01.homework

import java.util.*

fun main() {
    //결국초기화단계에서 6만큼이동 즉 1바이트만큼 (long형이기떄문)
//    val tempCheck = 1 shl 6
////    println(tempCheck)
//
//    val temp = BitSetMine()
////    println(temp.words.size)
////    println(temp.toString())
//
//    val tempSecond = BitSetMine(2)
////    println(tempSecond.words.size)
////    println(tempSecond.toString())
//    println(tempSecond.wordIndex(129))
//
//    println("========================================")
//    val test = BitSetMine(1)
//    println(test.size())
//
//    test.set(1)
//    test.set(2)
//
//    println(Long.MAX_VALUE)
//    println(test.toString())
//
//    test.set(10)
//
//    println(test.toString())
////    println(test.)
//
//    println(test.size())
//
//    test.set(64)
//    println(test.toString())
//    println(test.size())
//
//    test.set(10000)
//
//    println(test.size())
//
//    println(test.toString())

//    val test2 = BitSet()
    // 이게 64비트네..? 2에 6승이니까. 근데 숫자는 한개만생성하네??
//    println(1 shl 6)

    val test = BitSetMine(129)
    println("testSize - ${test.size()}") // 64 * 3 = 192

    test.set(55)

    println("testGet - ${test.get(55)}")

    val testTwo = BitSetMine(128)
    println("testTwoSize - ${testTwo.size()}") // 64 * 3 = 128

}

//default value - 64 bit으로 설정
class BitSetMine() {
    companion object {
        //1바이트가 Long타입이므로 그 만큼 늘려줘야하기 떄문 -> shiftLeft
        const val BIT_PER_WORD = 1 shl 6        // 64

        //Long 타입은 64비트 -> 비트가 6자리 이므로 6자리 정의
        const val ADDRESS_BITS_PER_WORD = 6
    }

    //글자수값 (Long타입) - 초기의 경우 그냥 64로 정의한다 (Long)
    var words: LongArray = initWords(BIT_PER_WORD)

    //내가 몇개의 Long값을 쓰고 있는지 관리한다.
    var wordInUse = 0

    constructor(bitNumber: Int) : this() {
//        this.nBits = bitNumber
        words = initWords(bitNumber)
    }

    /**
     * 실제 Long 배열의 index를 반환하는듯 (?)
     * */
    private fun wordIndex(bitIndex: Int): Int {
//        println("wordIndex - $bitIndex - ${bitIndex shr ADDRESS_BITS_PER_WORD} ")
        return bitIndex shr ADDRESS_BITS_PER_WORD
    }

    /**
     * 자리수를 반환해주는듯?
     * ex) 63 -> 0 , 65 -> 1 , 129 -> 2
     * */
    private fun initWords(bitNumber: Int): LongArray {
        return LongArray(wordIndex(bitNumber - 1) + 1) { 0 }
    }

    override fun toString(): String {
        return words.contentToString()
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
        //부족할 경우를 대비하여 배열을 확장해둔다.
        expandTo(wordIndex)

//        println("wordSize - ${words.size} and wordIndex - $wordIndex")
        //해당 index에 해당되는 숫자를 or 연산으로 더한다
        words[wordIndex] = words[wordIndex] or (1L shl bitIndex)
//        words[wordIndex] = words[wordIndex] or (1L shl bitIndex)
    }

    fun get(bitIndex : Int): Boolean {
        //index를 가져온다.
        val wordIndex = wordIndex(bitIndex)
        //해당 index에 해당되는 숫자를 and 연산으로 확인한다.
        return (wordIndex < wordInUse
                && words[wordIndex] and (1L shl bitIndex) != 0L)
    }

    private fun expandTo(wordIndex : Int) {
        val wordRequired: Int = wordIndex + 1
        if (wordInUse < wordRequired) {
            ensureCapacity(wordRequired)
            wordInUse = wordRequired
        }
    }

    private fun ensureCapacity(wordRequired : Int) {
        if (words.size < wordRequired) {
            val request = Math.max(2 * words.size, wordRequired)
//            println("ensureCapacity/request - $request")
            words = words.copyOf(request)
        }
    }
}