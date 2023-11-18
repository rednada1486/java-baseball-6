package baseball.view;

import baseball.domain.GameResult;

import static baseball.view.OutputMessage.GAME_END_MESSAGE;
import static baseball.view.OutputMessage.GAME_START_MESSAGE;

public class OutputView {
    public static void printGameStartMessage() {
        System.out.println(GAME_START_MESSAGE.getOutputMessage());
    }

    public static void printGameEndMessage() {
        System.out.println(GAME_END_MESSAGE.getOutputMessage());
    }

    public static void printGameEndMessageWithTryCount(int tryCount) {
        System.out.println(tryCount + "번의 시도 끝에 " + GAME_END_MESSAGE.getOutputMessage());
    }

    public static void printGameResult(GameResult gameResult) {
        System.out.println(gameResult.toString());
    }

    public static void printGameResultWithTryCount(GameResult gameResult, int tryCount) {
        System.out.println(gameResult.toString() + " (시도횟수:" + tryCount + ")");
    }
}
