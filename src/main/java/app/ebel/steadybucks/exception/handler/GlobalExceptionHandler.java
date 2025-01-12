package app.ebel.steadybucks.exception.handler;

import app.ebel.steadybucks.exception.ExceptionResponse;
import app.ebel.steadybucks.exception.community.CommunityException;
import app.ebel.steadybucks.exception.trading.TradingException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {CommunityException.class})
    public ResponseEntity<ExceptionResponse> handleCommunityException(RuntimeException ex, WebRequest request) {
        CommunityException communityException = (CommunityException) ex;
        ExceptionResponse response = ExceptionResponse.builder()
                .type(communityException.getClass().getCanonicalName())
                .errorCode(communityException.getErrorCode())
                .message(communityException.getMessage())
                .build();
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(value = {TradingException.class})
    public ResponseEntity<ExceptionResponse> handleTradingException(RuntimeException ex, WebRequest request) {
        TradingException tradingException = (TradingException) ex;
        ExceptionResponse response = ExceptionResponse.builder()
                .type(tradingException.getClass().getCanonicalName())
                .errorCode(tradingException.getErrorCode())
                .message(tradingException.getMessage())
                .build();
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<ExceptionResponse> handleGeneralException(RuntimeException ex, WebRequest request) {
        ExceptionResponse response = ExceptionResponse.builder()
                .type(ex.getClass().getCanonicalName())
                .errorCode("INTERNAL_SERVER_ERROR")
                .message("서버 내부에서 알 수 없는 오류가 발생했습니다.")
                .build();
        return ResponseEntity.internalServerError().body(response);
    }
}
