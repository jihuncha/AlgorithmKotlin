## Cracking the Oyster

<hr>

### before start....

* Merge Sort
  * Both merge sort and quicksort employ a common algorithmic paradigm based on recursion. This paradigm, divide-and-conquer, **breaks a problem into subproblems** that are similar to the original problem, recursively solves the subproblems, and finally combines the solutions to the subproblems to solve the original problem.

### Cracking the Oyster

* Question - How do I sort a disk file?
  * Friendly Conversation
    1. 왜 시스템이 제공하는 정렬을 사용하지않니?
       * 큰 시스템을 정렬하기위해, 기술적 이유로 제공하는 정렬 사용하지않음
    2. 정확히 무엇을 정렬하고 있습니까? 파일에 몇 개의 레코드가 있습니까? 각 레코드의 형식은 무엇입니까?
       * 파일에는 최대 천만 개의 레코드가 포함되어 있습니다. 각 레코드는 7자리 정수입니다.
    3. 파일이 그렇게 작은 경우 왜 메인 메모리에서 정렬하지 않습니까?
       *  시스템자체가 큰 시스템이므로 (?)
    4. 더 알아야할 것들?
       * 각각의 데이터는 7자리 양의 정수이며 같은 정수는 두 번 이상 나타날 수 없습니다.
       

* toll-free (수신자 부담 전화)

* Problem
  * The programmer was building a small corner of a system for processing such a database, and the integers to be sorted were toll-free telephone numbers. <br>
  * ![chap01.problem](../../img/section01/chap01.problem.png) <br>
  * 문제 : 한정된 메모리를 활용하여 파일을 읽어들여 정렬 후 다시 디스크에 쓰는 함수 작성하여야 함
  * 입력 : 최대 n개의 양의 정수를 포함하는 파일로, 각 숫자는 n보다 작고, n= 10^7임. 중복 없음. 정수만 존재
  * 출력 : 입력된 정수를 오름차순으로 정렬한 리스트
  * 제약사항 : 메모리 1MB만 사용 가능함. 디스크 공간은 충분, 10초~2분 실행시간 안에 완료 되어야 함
  
* Program Design
  1. The obvious program uses a general disk-based Merge Sort as a starting point but trims it to exploit the fact that we are sorting integers. That reduces the two hundred lines of code by a few dozen lines, and also makes it run faster. It might still take a few days to get the code up and running.
  2. 1MB = 1000kb = 1000000byte <br> 그래서 약 143000 (100만 / 7바이트) 을 1MB에 저장이 가능하다 <br>
     하지만 각 숫자를 32비트 정수로 나타내면 메가바이트에 250,000개의 숫자를 저장할 수 있습니다. (4바이트)
     즉 25만 * 40 -> 1000만 이므로 40번 반복하면된다(?)
  3. 세 가지 풀이법이 제공되는 것으로 보여짐 (?)
     1. Merge-sort Program - 파일을 한번 읽고, 여러번 읽고 쓰고(?) 마지막 출력파일에 쓴다.
     2. 40-pass algorithms - 파일을 여러번 읽고 결과를 한번에 write한다. 
     3. 1,2의 장점을 결합한 3번 방식을 원한다. 입력을 한 번만 읽고 중간 파일을 사용하지 않는 방식. -> 적절한 방식을 생각하시오.
  
<br>

* Implementation Sketch (구현 스케치)
  1. set {1, 2, 3, 5, 8, 13} <br>
     0 1 1 1 0 1 0 0 1 0 0 0 0 1 0 0 0 0 0 0 
     ![chap01.set](../../img/section01/chap01.set.png) <br>
  2. 7자리 정수는 1000만보다 작다
  3. 파일의 정수 집합을 나타내는 비트맵 데이터 구조가 주어지면 프로그램은 세 가지 자연스러운 단계로 작성될 수 있습니다. 
  첫 번째 단계에서는 모든 비트를 꺼서 세트를 비어 있도록 초기화합니다.
  두 번째 단계에서는 파일의 각 정수를 읽고 적절한 비트를 켜서 set을 빌드합니다.
  세 번째 단계는 각 비트를 검사하고 비트가 1인 경우 적절한 정수를 작성하여 정렬된 출력 파일을 생성합니다.
     
* Principles (원리)
  1. 문제 파악 (올바르게 파악했는가) - 문제 10,11,12 참고
  2. 비트맵 데이터 구조(?) - 문제 6,8
  3. 다중 패스 알고리즘 - 문제 5
  4. 시간과 공간의 Tradeoff - 시간을 더 사용하면 적은 공간 활용으로 가능하다
  5. Simple한 프로그래밍 디자인

* 결론
  * 작은 문제를 주의 깊게 분석하면 때때로 엄청난 실질적인 이점을 얻을 수 있다
  * 풀이는 **정수의 존재 자체를 hashtable에 만들어서 사용한다는것**
  * 1000000 byte -> 8000000 bit 이므로, 2번의 disk access 를 통하여 가능
  
* My Opinion
  * 예전에 알고리즘 공부할떄 dp(dynamic programming) 로 해결가능하지않을까?
    * dp가아닌 map에저장하는형식??
  * 어차피 만약 count숫자가 2인 경우는 fatal error 로 체크하면될듯??

* Problem
  1. 메모리가 부족하지 않다면 집합을 표현하고 정렬하기 위한 라이브러리가 있는 언어에서 정렬을 어떻게 구현하겠습니까?
    * just use sort
  2. 비트 논리 연산(예: and, or 및 shift)을 사용하여 비트 벡터를 구현하는 방법
    * https://www.programiz.com/kotlin-programming/bitwise in kotlin
    * 비트 백터 - 코틀린으로 BitSet을 이용하여 구현한다.
  3. 런타임 효율성은 설계 목표의 중요한 부분이었고 결과 프로그램은 충분히 효율적이었습니다. 
  시스템에서 비트맵 정렬을 구현하고 실행 시간을 측정하십시오. 
  시스템 정렬 및 문제 1의 정렬과 어떻게 비교됩니까? 
  n이 10,000,000이고 입력 파일에 1,000,000개의 정수가 포함되어 있다고 가정합니다.
     * 제대로한지 모르겠지만, 10배정도 차이가나는거같음..? [problem03](/study/section01/problem03/RuntimeEfficient.kt)
  
  4. 문제 3을 심각하게 받아들이면 중복 없이 n보다 작은 k 정수를 생성하는 문제에 직면하게 됩니다. 
  가장 간단한 접근 방식은 처음 k개의 양의 정수를 사용합니다. 
  이 극단적인 데이터 세트는 비트맵 방법의 실행 시간을 크게 변경하지 않지만 시스템 정렬의 실행 시간을 왜곡할 수 있습니다. 
  무작위 순서로 0과 n – 1 사이의 k개의 고유한 무작위 정수 파일을 어떻게 생성할 수 있습니까? 
  또한 효율적인 단기 프로그램을 위해 노력하십시오.
  5. d

### Todo

1. Tim Sort란?


### Homework
* 220307
1. bitset직접 만들어보기.. -> 그리고 시간 재보기. (신짱은 파이선 속도가 왜 더 느린지 알아오기)
   * [BitSetMine](/study/section01/homework/BitSetMine.kt)
2. 문제 2번 풀이 헤석
    * C언어는 int 32bit - > 2의 5승
    * 자바/코틀린 bitset은 long[]로 구현됨 -> long 64 bit -> 2의 6승
    * 자바는 왜 6씩 shift하냐
      * https://www.codeshelper.com/article/34286.html
      * int보다 long이 한자리 많아서(??)
    * 왜 int형을 안쓰고 Long을쓰냐??
      * https://stackoverflow.com/questions/32110554/why-is-the-internal-data-of-bitset-in-java-stored-as-long-instead-of-int-in
      * 명확한 해설이 없으나, 메모리 효율?때문인것으로 보여짐..      

* 220404
1. 6번까지 해오기..꼭!!
2. 4번
    * 문제 3을 심각하게 받아들이면 중복 없이 n보다 작은 k 정수를 생성하는 문제에 직면하게 됩니다. 
    * 가장 간단한 접근 방식은 처음 k개의 양의 정수를 사용합니다. 
    * 이 극단적인 데이터 세트는 비트맵 방법의 실행 시간을 크게 변경하지 않지만 시스템 정렬의 실행 시간을 왜곡할 수 있습니다. 
    * 무작위 순서로 0과 n – 1 사이의 k개의 고유한 무작위 정수 파일을 어떻게 생성할 수 있습니까? 또한 효율적인 단기 프로그램을 위해 노력하십시오.
    * 풀이 - [ProblemFour](/study/section01/problem04/ProblemFour.kt)
    * 해석 
      * 문제의 의도는 무엇인가? 예전에는 randomint를 스와핑으로 해결했다는 뜻인가..? 
      * 현재 코드로써는 Random 함수나 nextint 함수등이 다수 존재
      * nextInt 함수 파헤치기..나중에해보기!!
      
3. 5번
    * 프로그래머는 자신에게 약 메가바이트의 무료 저장 공간이 있지만 우리가 스케치한 코드는 1.25MB를 사용한다고 말했습니다. 
    * 그는 큰 어려움 없이 여분의 공간을 샅샅이 뒤질 수 있었습니다. 
    * 메가바이트가 단단하고 빠른 경계였다면 무엇을 추천하시겠습니까? 알고리즘의 실행 시간은 얼마입니까?
    * 풀이 - 정답보고풀어보기 ㅠ
    * 해석
      * millon - 1000000 = 10의 6승
      * 문제에서 천만의 숫자 (10의 7승) 를 표현할려면 천만 비트 = 1.25 x 밀리언 (1000000) 바이트가 필요하다 (천만 나누기 8)
      * Employing the fact that no phone numbers begin with the digits zero or one reduces the memory to exactly one million bytes.
      * 미국에서 전화번호 시작은 0,1로 시작을 절대 안한다는 뜻 = 2~9 .. 맨앞에만 근데 이게 왜 1메가바이트가될까??
      * 2단계 알고리즘은 먼저 5,000,000/8 = 625,000 단어 저장을 사용하여 0에서 4,999,999까지의 정수를 정렬한 
      * 다음 두 번째 단계에서 5,000,000에서 9,999,999까지를 정렬합니다. 
      * k-패스 알고리즘은 시간 kn 및 공간 n/k에서 n보다 작은 최대 n개의 반복되지 않는 양의 정수를 정렬합니다.

4. 6번 
   * 각 정수가 최대 한 번 나타날 수 있다고 말하는 대신 각 정수가 최대 10번 나타날 수 있다고 말하면 프로그래머에게 무엇을 추천하시겠습니까? 
   * 사용 가능한 스토리지의 양에 따라 솔루션이 어떻게 변경됩니까?
   * 풀이 - 결국 10까지 카운트하는 half-byte를 사용하여 구현한다 (?)
   * 해석 
     * hash 사용해서 [Number, Count] 형식으로 저장하면 되지않을까?
     * k-pass algorithm의 정확한 정의를 모르겟음..어떻게해석해야할까?


### 참고자료
  * gitignore - https://stackoverflow.com/questions/29386242/android-studio-gitignore-wont-ignore-iml
  * 다른분 해설 - https://fist0512.tistory.com/139


