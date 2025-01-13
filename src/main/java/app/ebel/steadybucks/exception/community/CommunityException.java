package app.ebel.steadybucks.exception.community;

import app.ebel.steadybucks.exception.AbstractException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
public class CommunityException extends AbstractException {
    private static final String DEFAULT_MESSAGE = "커뮤니티 서비스에 오류 발생.";
    private static final String ERROR_CODE = "COMMUNITY_ERROR";

    public CommunityException() {
        super(DEFAULT_MESSAGE, ERROR_CODE);
    }

    public CommunityException(String customMessage, String errorCode) {
        super(customMessage, errorCode);
    }
}
