package baseball.view;

public enum InputMessage {
    START_BASEBALL_GAME("숫자 야구게임을 시작합니다."),
    PLEASE_ENTER_THE_ANSWER("숫자를 입력해주세요 : "),
    RESTART_OR_QUIT("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");


    private final String inputMessage;

    InputMessage(String inputMessage) {
        this.inputMessage = inputMessage;
    }

    public String getInputMessage() {
        return inputMessage;
    }

    @Override
    public String toString() {
        return inputMessage;
    }
}
