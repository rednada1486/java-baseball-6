package baseball.controller;

import baseball.domain.Game;
import baseball.domain.GameResult;

import java.util.List;
import java.util.stream.Collectors;

import static baseball.service.NumberGenerator.pickNumbersInRangeWithoutDuplicates;
import static baseball.view.ErrorMessage.NUMBER_COMBINATION_IS_INCORRECT;
import static baseball.view.InputView.readUserAnswer;
import static baseball.view.OutputView.printGameResult;

public class GameController {
    public void play() {
        boolean isContinue = true;

        while (isContinue) {
            playGame();
            isContinue = askContinue();
        }
    }

    private boolean askContinue() {
        return false;
    }

    public void playGame() {
        Game game = new Game(pickNumbersInRangeWithoutDuplicates(1, 9, 3));
        askUntilCorrectAnswer(game);
    }

    public List<Integer> askAnswer() {
        String userAnswer = readUserAnswer();
        validateUserAnswer(userAnswer);
        return userAnswerToUserNumbers(userAnswer);
    }

    public void askUntilCorrectAnswer(Game game) {
        while (!game.isUserWin()) {
            List<Integer> userNumbers = askAnswer();
            GameResult gameResult = game.makeGameResult(userNumbers);
            printGameResult(gameResult);
        }
    }

    private void validateUserAnswer(String userAnswer) {
        if (userAnswer.length() != 3) {
            throw new IllegalArgumentException(NUMBER_COMBINATION_IS_INCORRECT.getName());
        }

        if (!isIntegerNum(userAnswer) || hasZero(userAnswer) || hasDuplicateNumber(userAnswer)) {
            throw new IllegalArgumentException(NUMBER_COMBINATION_IS_INCORRECT.getName());
        }
    }

    private boolean hasZero(String userAnswer) {
        return userAnswer.contains("0");
    }

    private boolean hasDuplicateNumber(String userAnswer) {
        return userAnswer.chars().distinct().count() != userAnswer.length();
    }

    private boolean isIntegerNum(String userAnswer) {
        try {
            Integer.parseInt(userAnswer);
        } catch (NumberFormatException e) {
            return false;
        }

        return true;
    }

    private List<Integer> userAnswerToUserNumbers(String userInput) {
        return userInput.chars().map(Character::getNumericValue).boxed().collect(Collectors.toList());
    }
}
