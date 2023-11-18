package baseball.view;

import static baseball.view.InputMessage.PLEASE_ENTER_THE_ANSWER;
import static baseball.view.InputMessage.RESTART_OR_QUIT;
import static camp.nextstep.edu.missionutils.Console.readLine;

public class InputView {
    public static String readUserAnswer() {
        System.out.print(PLEASE_ENTER_THE_ANSWER.getInputMessage());
        return readLine();
    }

    public static String readContinue() {
        System.out.print(RESTART_OR_QUIT.getInputMessage());
        return readLine();
    }
}
