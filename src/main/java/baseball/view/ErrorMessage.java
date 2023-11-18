package baseball.view;

public enum ErrorMessage {
    NUMBER_COMBINATION_IS_INCORRECT("[ERROR] : 잘못된 숫자 조합을 입력하였습니다.");

    private final String name;

    ErrorMessage(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

