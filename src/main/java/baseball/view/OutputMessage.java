package baseball.view;

public enum OutputMessage {
    GAME_START_MESSAGE("숫자 야구 게임을 시작합니다."),
    GAME_END_MESSAGE("3개의 숫자를 모두 맞히셨습니다! 게임 종료");


    private final String name;

    OutputMessage(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
