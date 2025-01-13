package app.ebel.steadybucks.exception;

import lombok.Getter;

@Getter
public abstract class AbstractException extends RuntimeException{

    private final String errorCode;

    protected AbstractException(String defaultMessage, String errorCode) {
        super(defaultMessage);
        this.errorCode = errorCode;
    }

}
