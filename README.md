# ✨ 프로젝트 소개

이 프로젝트는

**<span style="color:royalblue">우아한 테크코스 6기 프리코스</span>**

**<span style="color:red">(1주차 미션) 숫자 야구</span>**  입니다.

<br>

```
<숫자 야구 프로젝트 간단 설명>

1~9까지의 숫자 중에 3개의 숫자 순열이 정답으로 채택됩니다. (예:123)

사용자는 정답 숫자 순열을 맞추면 됩니다. 

사용자가 입력한 숫자 순열에서 (예:134)

같은 수, 같은 자리이면 1스트라이크 (예: 1은 같은 숫자 같은 자리라서 스트라이크)

같은 수, 다른 자리이면 1볼 (예: 3은 같은 숫자 같은 자리라서 볼)

다른 수면 1out 으로 결과를 출력해줍니다. (예: 4은 다른 숫자라서 아웃)

여기서 최종 결과는 1스트라이크 1볼 1아웃 입니다.

출력되는 결과를 보면서 정답 순열을 추리해가는 게임입니다.
```

![참고](https://velog.velcdn.com/images/rednada1486_/post/7fe5e91e-c2ac-4a29-96ce-0b1cadb2bf0a/image.gif)

<br><br><br>

# ✨ 프로젝트 구조

**<span style="color:red">MVC 패턴을 적용해서</span>**

프로젝트를 만들어 보았습니다.

![프로젝트 구조](https://velog.velcdn.com/images/rednada1486_/post/2d794581-3270-4a6c-993f-3dd4fc5e1ca9/image.png)

<br>

💼 Model : 비즈니스 로직을 담당
```
domain
    ㄴ Game : 정답 숫자 조합을 담고 있는 클래스, 사용자 숫자를 입력하면 게임 결과를 반환해주는 역할 수행
    
    ㄴ GameResult : 사용자가 입력한 숫자조합에 대한 ball, strike, out 개수를 계산하여 반환하는 클래스
    
    ㄴ Result(enum) : BALL, STRIKE, OUT 등 게임 결과와 관련된 상수값을 모아놓은 enum
    
service
    ㄴ GameResultCalculator : 숫자 야구 게임의 정답과 사용자가 입력한 숫자 조합을 비교하여 게임 결과를 계산하는 역할을 담당
    
    ㄴ NumberGenerator : 해당 주문에 적용될 혜택을 계산하는 역할을 담당
```

<br>

👁️ View : 사용자에게 데이터를 시각적으로 표시하는 부분

```
view
    ㄴ InputView : 사용자에게 입력을 받는 화면을 구성하는 역할을 담당
    
    ㄴ OutputView : 사용자에게 보여줄 화면을 구성하는 역할을 담당
    
    ㄴ ErrorMessage(enum) : 예외 메시지들을 모아놓은 enum 클래스
    
    ㄴ InputMessage(enum) : 사용자 입력 화면에 표시되는 메시지들을 모아놓은 enum 클래스
    
    ㄴ OutputMessage(enum) : 사용자 출력 화면에 표시되는 메시지들을 모아놓은 enum 클래스
```

<br>

🕹️ Controller : Model과 View 사이의 상호작용을 조정하고 제어하는 부분

``` 
controller
    ㄴ GameController : 숫자 야구 게임을 생성하고, 사용자의 입력에 대한 결과를 계산하고 출력하는 등 전반적인 게임 흐름을 제어하는 클래스
```


<br><br><br>

# ✨ 기능 구현  목록


## 📕 숫자 야구 게임의 정답을 입력하면 그 결과를 계산하고 출력하는 기능 (핵심기능)

```
✏️ 기능1. 사용자로부터 정답을 입력받는다. ➡ InputView.readUserAnswer()

✏️ 기능2. 정답 숫자 조합을 생성한다. ➡ NumberGenerator.pickNumbersInRangeWithoutDuplicates()

✏️ 기능3. 정답 숫자 조합과 사용자가 입력한 숫자 조합을 비교해서 STRIKE 개수를 계산한다. ➡ GameResultCalculator.calculateStrikeCount()

✏️ 기능4. 정답 숫자 조합과 사용자가 입력한 숫자 조합을 비교해서 ball, strike, out 개수를 계산하고 Result 객체에 담아서 반환한다. ➡ GameResultCalculator.calculateBallCount()

✏️ 기능5. Result를 콘솔 출력용 문자열로 바꾼다. ➡ domain.GameResult.toString()

✏️ 기능6. 사용자가 입력한 숫자조합을 검증 후 이상이 없으면 List 형태로 변환한다. ➡ GameController.askAnswer()

✏️ 기능7. 사용자가 입력한 숫자 조합을 가지고 게임 결과를 계산한다. ➡ domain.Game.makeGameResult()

✏️ 기능8. (테스트) 사용자가 정답을 맞춘 경우, isUserWin을 true로 설정된다. ➡ domain.Game.makeGameResult()

✏️ 기능9. 사용자가 정답을 맞출 때 까지 계속 물어본다. ➡ GameController.askUntilCorrectAnswer()

✏️ 기능10. 숫자야구 게임을 생성하고 사용자가 정답을 맞출 때까지 계속 진행한다. ➡ GameController.playGame()
```

<br>

## 📕 사용자가 입력한 정답을 검증하는 기능 (부가기능)

```
✏️ 기능11. 사용자가 입력한 숫자야구 정답 문자열을 검증한다. ➡ GameController.validateUserAnswer()

✏️ 기능12. (테스트) 사용자가 입력한 값이 숫자가 아니면 예외가 발생한다. ➡ GameController.validateUserAnswer()

✏️ 기능13. (테스트) 사용자가 입력한 값이 3자리가 아니면 예외가 발생한다. ➡ GameController.validateUserAnswer()

✏️ 기능14. (테스트) 사용자가 입력한 값에 0이 포함되어 있으면 예외가 발생한다. ➡ GameController.validateUserAnswer()

✏️ 기능15. (테스트) 사용자가 입력한 값에 중복된 숫자가 있으면 예외가 발생한다. ➡ GameController.validateUserAnswer()
```

<br>

## 📕 숫자 야구 게임 재시작 기능 (부가기능)

```
✏️ 기능16. 사용자에게 게임이 종료되었음을 알린다. ➡ OutputView.printGameEndMessage()

✏️ 기능17. 사용자의 숫자야구 게임 재시작 여부를 입력받는다. ➡ InputView.readContinue()

✏️ 기능18. 사용자에게 게임 재시작 여부를 입력받고, 입력값에 따라 true 혹은 false를 반환한다. ➡ GameController.askContinue()

✏️ 기능19.  (테스트) 사용자가 입력한 재시작 여부 값이 1 또는 2가 아니면 예외가 발생한다. ➡ GameController.validateContinueValue()
```




<br><br><br>

# ✨ 이번 프로젝트에서 중점을 둔 부분

## 📌 1. MVC 패턴 적용 :

```
이 프로젝트는 1주차에 진행했던 프로젝트였습니다.

프리코스 과정이 끝나고 복습을 겸해서

MVC 패턴을 적용해서 리팩토링을 시도해보았습니다.
```

<br>

## 📌 2. 선 핵심기능 후 부가기능 :

```
핵심기능을 포함한 동작가능한 프로그램을 먼저 만들고

그 후 부가기능을 점점 추가해 나가는 방식으로 프로그래밍을 진행하였습니다.
```
![참고그림](https://velog.velcdn.com/images/rednada1486_/post/64d6415d-3f92-48d7-a14e-4084f5198ca7/image.png)

