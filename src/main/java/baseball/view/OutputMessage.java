package baseball.view;

public enum OutputMessage {
    GAME_START_MESSAGE("숫자 야구 게임을 시작합니다."),
    GAME_END_MESSAGE("3개의 숫자를 모두 맞히셨습니다! 게임 종료\n");


    private final String outputMessage;

    OutputMessage(String name) {
        this.outputMessage = name;
    }

    public String getOutputMessage() {
        return outputMessage;
    }
}
