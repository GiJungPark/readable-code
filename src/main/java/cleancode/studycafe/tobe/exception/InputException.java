package cleancode.studycafe.tobe.exception;

public class InputException extends AppException {

    private static final String DEFAULT_MESSAGE = "잘못된 입력입니다.";

    public InputException() {
        super(DEFAULT_MESSAGE);
    }

    public InputException(String message) {
        super(message);
    }
}
