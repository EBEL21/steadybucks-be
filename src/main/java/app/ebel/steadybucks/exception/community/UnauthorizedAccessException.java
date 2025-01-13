package app.ebel.steadybucks.exception.community;

public class UnauthorizedAccessException extends CommunityException {

    private static final String DEFAULT_MESSAGE = "권한이 부족합니다.";
    private static final String ERROR_CODE = "UNAUTHORIZED_USER";

    public UnauthorizedAccessException() {
        super(DEFAULT_MESSAGE, ERROR_CODE);
    }

    public UnauthorizedAccessException(String customMessage) {
        super(customMessage, ERROR_CODE);
    }
}
