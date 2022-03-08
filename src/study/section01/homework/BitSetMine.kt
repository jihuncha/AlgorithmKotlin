package study.section01.homework

//class BitSetMine(range:Int) {
//    constructor()
//}

fun main() {
    //결국초기화단계에서 6만큼이동 즉 1바이트만큼 (long형이기떄문)
    val tempCheck = 1 shl 6
    println(tempCheck)

    BitSetMine(20)
}

class BitSetMine(var nBits : Int = 1 shl 6) {

}