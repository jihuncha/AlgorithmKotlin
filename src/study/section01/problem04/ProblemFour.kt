package study.section01.problem04


//중복이 안되는 랜덤한 숫자를 만들기
//무작위 순서로 0과 n – 1 사이의 k개의 고유한 무작위 정수 파일을 어떻게 생성할 수 있습니까
fun main() {
    // k 개
    val k = 50

    // 0 ~ N-1
    val n = 100

    //범위 설정
    val range = (0..n-1)

    // 결과를 담을 List
    val resultList = mutableListOf<Int>()
    // 인트 생성
    for(i in 1 .. k) {
        resultList.add(range.random())
    }

    println(resultList)
}