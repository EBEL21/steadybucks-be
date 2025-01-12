package app.ebel.steadybucks.exception.trading;

import app.ebel.steadybucks.exception.AbstractException;
import lombok.Getter;

@Getter
public class TradingException extends AbstractException {

    private static final String DEFAULT_MESSAGE = "트레이딩 관련 서비스에 오류 발생.";
    private static final String ERROR_CODE = "TRADING_ERROR";

    public TradingException() {
        super(DEFAULT_MESSAGE, ERROR_CODE);
    }

    public TradingException(String customMessage) {
        super(customMessage, ERROR_CODE);
    }
}
