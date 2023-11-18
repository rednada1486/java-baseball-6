package baseball.view;

import baseball.domain.GameResult;

import static baseball.view.OutputMessage.GAME_END_MESSAGE;
import static baseball.view.OutputMessage.GAME_START_MESSAGE;

public class OutputView {
    public static void printGameStartMessage() {
        System.out.println(GAME_START_MESSAGE.getName());
    }

    public static void printGameEndMessage() {
        System.out.println(GAME_END_MESSAGE.getName());
    }

    public static void printGameResult(GameResult gameResult) {
        System.out.println(gameResult.toString());
    }
}
