### Kotlin Algorithm

#### Sort

* sortedby
* sortedwith -> 두 가지 요소 이상으로

* CompareTo

#### Kotlin Coroutine and Flow

* https://dalinaum.github.io/coroutines-example/

1. **예제 1: 간단한 코루틴** <br>
   아주 간단한 코루틴을 수행해봅시다. 코루틴을 만드는 가장 간단한 함수는 runBlocking이라고 합니다. 이렇게 코루틴을 만드는 함수를 코루틴 빌더라고 합니다. runBlocking은 코루틴을 만들고 코드
   블록이 수행이 끝날 때까지 runBlocking 다음의 코드를 수행하지 못하게 막습니다. 그래서 블로킹(blocking)인거죠. <br>

2. **예제 2: 코루틴 빌더의 수신 객체** <br>
   runBlocking안에서 this를 수행하면 코루틴이 수신 객체(Receiver)인 것을 알 수 있습니다.
   "coroutine#1":BlockingCoroutine{Active}@3930015a 이런 형태의 결과가 나옵니다. BlockingCoroutine은 CoroutineScope의 자식입니다. 코틀린 코루틴을
   쓰는 모든 곳에는 코루틴 스코프(CoroutineScope)가 있다고 생각하면 됩니다. 코루틴의 시작은 코루틴 스코프다. 외웁시다. <br>

3. **예제 3: 코루틴 컨텍스트** <br>
   코루틴 스코프는 코루틴을 제대로 처리하기 위한 정보, 코루틴 컨텍스트(CoroutineContext)를 가지고 있습니다. 수신 객체의 coroutineContext를 호출해 내용을 확인해봅시다. <br>

4. **예제 4: launch 코루틴 빌더** <br>
   이제 코루틴 내에서 다른 코루틴을 수행해 봅시다. 이번에는 launch란 빌더를 사용해서 코드를 수행합니다. launch는 코루틴 빌더입니다. 새로운 코루틴을 만들기 때문에 새로운 코루틴 스코프를 만들게
   되고요. launch는 “할 수 있다면 다른 코루틴 코드를 같이 수행”시키는 코루틴 빌더입니다. <br>
launch 코루틴 빌더에 있는 내용이 runBlocking이 있는 메인 흐름 보다 늦게 수행된 것을 볼 수 있습니다. 둘 다 메인 스레드(main)를 사용하기 때문에 runBlocking의 코드들이 메인 스레드를
다 사용할 때 까지 launch의 코드 블록이 기다리는 것입니다. runBlocking은 Hello를 출력하고 나서 종료하지는 않고 launch 코드블록의 내용이 다 끝날 때까지 기다립니다. <br>

5. **예제 5: delay 함수** <br>
   Hello를 조금 더 늦게 수행시키기 위해서는 delay 함수를 호출해 봅시다. 인자로 밀리세컨드 단위의 시간을 지정할 수 있습니다.

6. **예제 6: 코루틴 내에서 sleep** <br>
   우리가 잘 아는 Thread.sleep을 호출하면 어떻게 될까요? 우리가 원하는 결과가 나오지 않았을 것입니다. Thread.sleep을 하면 코루틴이 아무 일을 하지 않는 동안에도 스레드를 양보하지 않고
   독점합니다.
7. 