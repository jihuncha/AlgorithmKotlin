## Kotlin Flow Study

### Kotlin Coroutine and Flow

* https://dalinaum.github.io/coroutines-example/
* https://kotlinworld.com/ - 정리 정말 너무 잘되있으심..존경

#### Chap01 처음 만나는 코루틴

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
   Hello를 조금 더 늦게 수행시키기 위해서는 delay 함수를 호출해 봅시다. 인자로 밀리세컨드 단위의 시간을 지정할 수 있습니다. <br>

6. **예제 6: 코루틴 내에서 sleep** <br>
   우리가 잘 아는 Thread.sleep을 호출하면 어떻게 될까요? 우리가 원하는 결과가 나오지 않았을 것입니다. Thread.sleep을 하면 코루틴이 아무 일을 하지 않는 동안에도 스레드를 양보하지 않고
   독점합니다. <br>

7. **예제 7: 한번에 여러 launch** <br>
   1, 2, 3을 순서대로 수행시켜봅시다. 딜레이 값을 바꿔 보면 suspend된 이후 깨어나는 순서에 따라 출력 결과가 달라집니다. <br>

8. **예제 8: 상위 코루틴은 하위 코루틴을 끝까지 책임진다.** <br>
   runBlocking 안에 두 launch가 속해 있는데 계층화되어 있어 구조적입니다. runBlocking은 그 속에 포함된 launch가 다 끝나기 전까지 종료되지 않습니다.<br>

9. **예제 9: suspend 함수** <br>
   delay, launch 등 지금까지 봤던 함수들은 코루틴 내에서만 호출 할 수 있습니다. 
그럼 이 함수들을 포함한 코드들을 어떻게 함수로 분리할 수 있을까요? 
코드의 일부를 함수로 분리할 때는 함수의 앞에 suspend 키워드를 붙이면 됩니다. 
doOne은 delay와 같은 함수(suspend인 함수)를 호출하지 않았기 때문에 suspend를 붙이지 않은 일반 함수로 해도 됩니다.
만약 suspend 함수를 다른 함수에서 호출하려면 그 함수가 suspend 함수이거나 코루틴 빌더를 통해 코루틴을 만들어야 합니다. <br>

#### Chap02 스코프빌더와 잡

10. **예제 10: suspend 함수에서 코루틴 빌더 호출** <br>
    코루틴 빌더를 suspend 함수 안에서 호출하면 어떻게 될까요? - 코루틴 빌더는 코루틴 스코프 내에서만 호출해야 합니다. <br>

11. **예제 11: 코루틴 스코프** <br>
    코루틴 스코프를 만드는 다른 방법은 스코프 빌더를 이용하는 것입니다. coroutineScope를 이용해보세요.
코루틴 스코프는 runBlocking을 썼을 때와 모양이 거의 비슷합니다.
하지만 둘의 차이가 있는데 **runBlocking은 현재 쓰레드를 멈추게 만들고, 기다리지만 coroutineScope는 현재 스레드를 멈추게 하지 않습니다.** 
호출한 쪽이 suspend되고 시간이 되면 다시 활동하게 됩니다. <br>

12. **예제 12: Job을 이용한 제어** <br>
    코루틴 빌더 launch는 Job객체를 반환하며 이를 통해 종료될 때까지 기다릴 수 있습니다. <br>

13. **예제 13: 가벼운 코루틴** <br>
    코루틴은 협력적으로 동작하기 때문에 여러 코루틴을 만드는 것이 큰 비용이 들지 않습니다. 
10만개의 간단한 일을 하는 코루틴도 큰 부담은 아닙니다.
(하지만 코틀린 플레이그라운드의 한계로 그렇게 많은 코루틴은 로그를 찍지 못합니다.) <br>

#### 취소와 타임아웃

14. **예제 14: Job에 대해 취소** <br>
    명시적인 Job에 대해 cancel 메서드를 호출해 취소할 수 있습니다, delay 간격들을 변경해 보면서 테스트 해보세요. <br>

15. **예제 15: 취소 불가능한 Job** <br>
    예제는 취소가 불가능한 Job입니다.
launch(Dispatchers.Default)는 그 다음 코드 블록을 다른 스레드에서 수행을 시킬 것입니다.
두가지 부분이 신경이 쓰입니다.
* job1이 취소든 종료든 다 끝난 이후에 doCount Done!을 출력하고 싶다.
* 취소가 되지 않았다.
먼저 취소든 종료든 다 끝난 이후에 doCount Done!을 출력합시다. <br>

16. **예제 16: cancel과 join** <br>
    cancel 이후에 join을 넣어서 실제로 doCount가 끝날 때 doCount Done!가 출력하게 했습니다. <br>

17. **예제 17: cancelAndJoin** <br>
    cancel을 하고 join을 하는 일은 자주 일어나는 일이기 때문에 한번에 하는 cancelAndJoin이 준비되어 있습니다. <br>

18. **예제 18: cancel 가능한 코루틴** <br>
    isActive를 호출하면 해당 코루틴이 여전히 활성화된지 확인할 수 있습니다. isActive를 루프에 추가해봅시다. <br>

19. **예제 19: finally를 같이 사용** <br>
    launch에서 자원을 할당한 경우에는 어떻게 정리해야할까요?
suspend 함수들은 JobCancellationException를 발생하기 때문에 표준 try catch finally로 대응할 수 있습니다. <br>

20. **예제 20: 취소 불가능한 블록** <br>
    어떤 코드는 취소가 불가능해야 합니다. withContext(NonCancellable)을 이용하면 취소 불가능한 블록을 만들 수 있습니다. 
    취소 불가능한 코드를 finally절에 사용할 수도 있습니다.<br>
21. **예제 21: 타임 아웃** <br>
    일정 시간이 끝난 후에 종료하고 싶다면 withTimeout을 이용할 수 있습니다. 취소가 되면 TimeoutCancellationException 예외가 발생합니다. <br>

22. **예제 22: withTimeoutOrNull** <br>
    예외를 핸들하는 것은 귀찮은 일입니다. withTimeoutOrNull을 이용해 타임 아웃할 때 null을 반환하게 할 수 있습니다. <br>
성공할 경우 whithTimeoutOrNull의 마지막에서 true를 리턴하게 하고 실패했을 경우 null을 반환할테니 엘비스 연산자(?:)를 이용해 false를 리턴하게 했습니다. 엘비스 연산자는 null 값인 경우에 다른 값으로 치환합니다.<br>
코틀린의 예외는 식(expression)이어 활용이 어렵지는 않습니다만 개인적으로는 null을 리턴하고 엘비스 연산자로 다루는게 더 편한 것 같습니다. <br>

#### 서스펜딩 함수 활용

23. **예제 23: suspend 함수들의 순차적인 수행** <br>
    순차적으로 suspend 함수를 먼저 수행시켜봅시다.
    대략 2000ms 이상 수행된다는 것을 볼 수 있습니다.
순차적으로 수행되었기 때문에 getRandom1이 1000ms 정도를 소비하고 getRandom2가 1000ms 정도 소비하는 것입니다. <br>

24. **예제 24: async를 이용해 동시 수행하기** <br>
    aync 키워드를 이용하면 동시에 다른 블록을 수행할 수 있습니다. launch와 비슷하게 보이지만 수행 결과를 await키워드를 통해 받을 수 있다는 차이가 있습니다.
결과를 받아야 한다면 async, 결과를 받지 않아도 된다면 launch를 선택할 수 있습니다. <br>
await 키워드를 만나면 async 블록이 수행이 끝났는지 확인하고 아직 끝나지 않았다면 suspend되었다 나중에 다시 깨어나고 반환값을 받아옵니다.
수행 결과를 보면 getRandom1과 getRandom2를 같이 수행해서 경과시간이 거의 반으로 줄어들었습니다. <br>
많은 다른 언어들이 async, await 키워드를 가지고 있는데 그것과는 비슷하게 보이지만 조금 다릅니다. 코틀린은 suspend 함수를 호출하기 위해 어떤 키워드도 필요하지 않습니다. 코틀린의 suspend가 다른 언어에서 async와 같다고 보시면 됩니다. <br>
async, await 짝을 맞추는 것은 Microsoft .net C#의 영향으로 일반화되었는데 어떠한 키워드를 붙이지 않는 Go언어의 양향을 받아 가능한 제거하려 노력했다고 합니다. 그럼에도 불구하고 Java언어와의 호환성 때문에 suspend(async) 키워드는 버릴 수 없었습니다. <br>

25. **예제 25: aync 게으르게 사용하기** <br>
    async 키워드를 사용하는 순간 코드 블록이 수행을 준비하는데, async(start = CoroutineStart.LAZY)로 인자를 전달하면 우리가 원하는 순간 수행을 준비하게 할 수 있습니다. 이후 start 메서드를 이용해 수행을 준비하게 할 수 있습니다. <br>

26. **예제 26: async를 사용한 구조적인 동시성** <br>
    코드를 수행하다 보면 예외가 발생할 수 있습니다. 예외가 발생하면 위쪽의 코루틴 스코프와 아래쪽의 코루틴 스코프가 취소됩니다. <br>
    getRandom2가 오류가 나서 getRandom1와 doSomething은 취소됩니다. (JobCancellationException 발생) 문제가 된 IllegalStateException도 외부에서 잡아줘야 합니다. <br>

#### 코루틴 컨텍스트와 디스패처

27. **예제 27: 코루틴 디스패처** <br>
    코루틴의 여러 디스패처 Default, IO, Unconfined, newSingleThreadContext을 사용해봅시다. <br>
    1. Default는 코어 수에 비례하는 스레드 풀에서 수행합니다.
    2. IO는 코어 수 보다 훨씬 많은 스레드를 가지는 스레드 풀입니다. IO 작업은 CPU를 덜 소모하기 때문입니다.
    3. Unconfined는 어디에도 속하지 않습니다. 지금 시점에는 부모의 스레드에서 수행될 것입니다.
    4. newSingleThreadContext는 항상 새로운 스레드를 만듭니다. <br>
28. **예제 28: async에서 코루틴 디스패처 사용** <br>
    launch외에 async, withContext 등의 코루틴 빌더에도 디스패처를 사용할 수 있습니다. <br>

29. **예제 29: Confined 디스패처 테스트** <br>
    Confined는 처음에는 부모의 스레드에서 수행됩니다. 하지만 한번 중단점(suspension point)에 오면 바뀌게 됩니다. <br>
    Confined는 중단점 이후 어느 디스패처에서 수행될지 예측하기 어렵습니다. 가능하면 확실한 디스패처를 사용합시다. <br>

30. **예제 30: 부모가 있는 Job과 없는 Job**<br>
    코루틴 스코프, 코루틴 컨텍스트는 구조화되어 있고 부모에게 계층적으로 되어 있습니다. 
    코루틴 컨텍스트의 Job 역시 부모에게 의존적입니다. 부모를 캔슬했을 때의 영향을 확인해보세요. <br>
    job.cancelAndJoin() 실행 후의 delay가 없다면 어떻게 될까요?<br>

31. ㅇㅇㅇㅇjj
32. ㅇ
33. ㅇ
34. d
35. d
36. d
37. d
38. d
39. d
40. 