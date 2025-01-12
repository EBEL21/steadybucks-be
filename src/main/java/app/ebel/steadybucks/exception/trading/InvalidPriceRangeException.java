package app.ebel.steadybucks.exception.trading;

import app.ebel.steadybucks.exception.AbstractException;

public class InvalidPriceRangeException extends TradingException {
    private static final String message = "올바르지 않은 가격 범위입니다.";
    public InvalidPriceRangeException() {
        super(message);
    }

    public InvalidPriceRangeException(String customMessage) {
        super(customMessage);
    }
}
