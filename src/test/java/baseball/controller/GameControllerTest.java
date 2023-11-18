package baseball.controller;

import baseball.domain.Game;
import camp.nextstep.edu.missionutils.Console;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;

import static baseball.view.ErrorMessage.NUMBER_COMBINATION_IS_INCORRECT;
import static baseball.view.ErrorMessage.ONLY_ONE_OR_TWO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class GameControllerTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    private GameController gameController;

    InputStream createUserInput(String input) {
        return new ByteArrayInputStream(input.getBytes());
    }

    @BeforeEach
    public void beforeEach() {
        System.setOut(new PrintStream(outputStreamCaptor));
        gameController = new GameController();
    }

    @AfterEach
    public void afterEach() {
        Console.close();
        System.setOut(standardOut);
    }

    @Test
    @DisplayName("askAnswer 메서드는 사용자 입력값을 변환하여 숫자 형태의 리스트로 반환한다.")
    void askAnswerShouldReturnUserNumbers() {
        // given
        System.setIn(createUserInput("123"));

        // when
        List<Integer> result = gameController.askAnswer();

        // then
        assertThat(result)
                .hasSize(3)
                .containsSubsequence(1, 2, 3);
    }

    @Test
    @DisplayName("사용자가 정답을 맞출 때까지 계속 물어보는 지 확인한다.")
    void askUntilCorrectAnswer() {
        // given
        System.setIn(createUserInput("123\n456\n789"));

        List<Integer> computerAnswer = List.of(7, 8, 9);
        Game game = new Game(computerAnswer);

        // when
        gameController.askUntilCorrectAnswer(game);
        String result = outputStreamCaptor.toString();
        int count = countSomeWord(result, "숫자를 입력해주세요 : ");

        // then
        assertThat(count).isEqualTo(3);
    }

    public int countSomeWord(String target, String someWord) {
        return target.split(someWord).length - 1;
    }

    @ParameterizedTest
    @ValueSource(strings = {"a", "1a", "1a#"})
    @DisplayName("사용자가 입력한 값이 숫자가 아니면 예외가 발생한다.")
    void shouldThrowExceptionWhenEnteredValueIsNotNumber(String input) {
        // given
        System.setIn(createUserInput(input));

        // when, then
        assertThatThrownBy(() -> gameController.askAnswer())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(NUMBER_COMBINATION_IS_INCORRECT.getName());
    }

    @ParameterizedTest
    @ValueSource(strings = {"1", "12"})
    @DisplayName("사용자가 입력한 값이 3자리가 아니면 예외가 발생한다.")
    void shouldThrowExceptionWhenEnteredValueLengthIsNotThree(String input) {
        // given
        System.setIn(createUserInput(input));

        // when, then
        assertThatThrownBy(() -> gameController.askAnswer())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(NUMBER_COMBINATION_IS_INCORRECT.getName());
    }

    @ParameterizedTest
    @ValueSource(strings = {"001", "012", "100"})
    @DisplayName("사용자가 입력한 값에 0이 포함이 되어있으면 예외가 발생한다.")
    void shouldThrowExceptionWhenEnteredValueContainsZero(String input) {
        // given
        System.setIn(createUserInput(input));

        // when, then
        assertThatThrownBy(() -> gameController.askAnswer())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(NUMBER_COMBINATION_IS_INCORRECT.getName());
    }

    @ParameterizedTest
    @ValueSource(strings = {"112", "122", "111"})
    @DisplayName("사용자가 입력한 값에 중복된 숫자가 있으면 예외가 발생한다.")
    void shouldThrowExceptionWhenEnteredValueHasDuplicateNumber(String input) {
        // given
        System.setIn(createUserInput(input));

        // when, then
        assertThatThrownBy(() -> gameController.askAnswer())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(NUMBER_COMBINATION_IS_INCORRECT.getName());
    }


    @Test
    @DisplayName("askContinue 메서드는 1을 입력받은 경우 true를 반환한다.")
    void askContinueShouldReturnTrueWhenInputtedNumberOne() {
        // given
        System.setIn(createUserInput("1"));

        // when
        boolean result = gameController.askContinue();

        // then
        assertThat(result).isEqualTo(true);
    }

    @Test
    @DisplayName("askContinue 메서드는 2를 입력받은 경우 false를 반환한다.")
    void askContinueShouldReturnFalseWhenInputtedNumberTwo() {
        // given
        System.setIn(createUserInput("2"));

        // when
        boolean result = gameController.askContinue();

        // then
        assertThat(result).isEqualTo(false);
    }

    @Test
    @DisplayName("askContinue 메서드는 잘못된 값을 입력받은 경우 예외를 발생시킨다.")
    void askContinueShouldThrowExceptionWhenWrongValueIsInputted() {
        // given
        System.setIn(createUserInput("3"));

        // when, then
        assertThatThrownBy(() -> gameController.askContinue())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ONLY_ONE_OR_TWO.getName());
    }
}