package app.ebel.steadybucks.exception.community;

public class UnmatchedPasswordException extends CommunityException{

    private static final String DEFAULT_MESSAGE = "패스워드가 일치하지 않습니다.";
    private static final String ERROR_CODE = "UNMATCHED_PASSWORD";

    public UnmatchedPasswordException() {
        super(DEFAULT_MESSAGE, ERROR_CODE);
    }

    public UnmatchedPasswordException(String customMessage) {
        super(customMessage, ERROR_CODE);
    }
}
