package baseball.controller;

import baseball.domain.Game;
import baseball.domain.GameResult;
import baseball.view.ErrorMessage;
import baseball.view.InputView;

import java.util.List;
import java.util.stream.Collectors;

import static baseball.service.NumberGenerator.pickNumbersInRangeWithoutDuplicates;
import static baseball.view.ErrorMessage.NUMBER_COMBINATION_IS_INCORRECT;
import static baseball.view.InputView.readUserAnswer;
import static baseball.view.OutputView.*;

public class GameController {
    public void play() {
        boolean isContinue = true;

        while (isContinue) {
            playGame();
            printGameEndMessage();
            isContinue = askContinueUntilPass();
        }
    }

    public boolean askContinue() {
        String userInput = InputView.readContinue();
        validateContinue(userInput);

        return "1".equals(userInput);
    }

    public boolean askContinueUntilPass() {
        return receiveInputUntilPass(this::askContinue);
    }

    private void validateContinue(String userInput) {
        if (!userInput.equals("1") && !userInput.equals("2")) {
            throw new IllegalArgumentException(ErrorMessage.ONLY_ONE_OR_TWO.getName());
        }
    }

    public void playGame() {
        printGameStartMessage();
        Game game = new Game(pickNumbersInRangeWithoutDuplicates(1, 9, 3));
        askUntilCorrectAnswer(game);
    }

    public List<Integer> askAnswer() {
        String userAnswer = readUserAnswer();
        validateUserAnswer(userAnswer);
        return userAnswerToUserNumbers(userAnswer);
    }

    public List<Integer> askAnswerUntilPass() {
        return receiveInputUntilPass(this::askAnswer);
    }

    public void askUntilCorrectAnswer(Game game) {
        while (!game.isUserWin()) {
            List<Integer> userNumbers = askAnswerUntilPass();
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

    public <T> T receiveInputUntilPass(ExceptionSupplier<T> inputMethod) {
        T result;

        while (true) {
            try {
                result = inputMethod.get();
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        return result;
    }
}
